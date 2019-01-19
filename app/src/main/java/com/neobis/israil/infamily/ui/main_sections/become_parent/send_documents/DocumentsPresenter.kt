package com.neobis.israil.infamily.ui.main_sections.become_parent.send_documents

import android.content.Intent
import android.os.Build
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log.e
import com.neobis.israil.infamily.BuildConfig
import com.neobis.israil.infamily.R
import com.neobis.israil.infamily.StartApplication
import com.neobis.israil.infamily.model.DocumentStatus
import com.neobis.israil.infamily.utill.Const
import com.neobis.israil.infamily.utill.FileUtils
import com.neobis.israil.infamily.utill.Permissions
import id.zelory.compressor.Compressor
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class DocumentsPresenter (val activity: AppCompatActivity, val view:DocumentsContract.View)
    :DocumentsContract.Presenter {

    private val map:HashMap<String,String> = HashMap()
    override fun updateApplcication() {
        if(isApplicationFilled()){
            val ownerId = StartApplication.sharedPreference.getInt(Const.OWNER_ID,0)
            val ownerNumber = StartApplication.sharedPreference.getString(Const.USER_PHONE,null)
            val bodyBuilder = MultipartBody.Builder()
            updateApplicaiton(bodyBuilder,activity)
            if(isViewAttached()){
                view.showProgress()
                StartApplication.service.updateDocumentStatus(bodyBuilder.build(),ownerId,ownerNumber!!)
                        .enqueue(object : Callback<DocumentStatus> {
                            override fun onFailure(call: Call<DocumentStatus>?, t: Throwable?) {
                                if(isViewAttached()){
                                    view.onFailureConnnectedWithServer()
                                    e("_______", t?.message)
                                    view.hideProgress()
                                }
                            }

                            override fun onResponse(call: Call<DocumentStatus>?, response: Response<DocumentStatus>?) {
                                if(isViewAttached()){
                                    if(response!!.isSuccessful && response.body() != null){
                                        view.onSuccessApplicationUpdated()
                                        view.hideProgress()
                                    }
                                }
                            }

                        })
            }
        }
        else view.onFailureApplicationFilled()
    }

    private fun updateApplicaiton(bodyBuilder: MultipartBody.Builder, activity: AppCompatActivity) {
        applyDocumentsToBodyBuilder(bodyBuilder)

    }

    private fun applyDocumentsToBodyBuilder(bodyBuilder: MultipartBody.Builder) {
        val array : Array<out String>? = activity.resources.getStringArray(R.array.server_document_titles)
        for(i in 0 until 6) {
            val imagePath = StartApplication.sharedPreference.getString(i.toString(), "null")
            if (imagePath != "null") {
                val file = File(imagePath)
                bodyBuilder.addFormDataPart(array?.get(i)!!, file.name, RequestBody.create(MediaType.parse(
                        "multipart/form-data"), file))
            }
            bodyBuilder.setType(MultipartBody.FORM)
        }
    }

    override fun sendApplciation() {
        if (isApplicationFilled()) {
            val bodyBuilder = MultipartBody.Builder()
            fillApplication(bodyBuilder, activity)
            if (isViewAttached()) {
                view.showProgress()
                StartApplication.service.sendApplication(bodyBuilder.build()).enqueue(
                        object : Callback<DocumentStatus> {
                            override fun onFailure(call: Call<DocumentStatus>?, t: Throwable?) {
                                if (isViewAttached()) {
                                    view.hideProgress()
                                    view.onFailureConnnectedWithServer()
                                }
                            }

                            override fun onResponse(call: Call<DocumentStatus>?, response: Response<DocumentStatus>?) {
                                if (isViewAttached()) {
                                    if (response!!.isSuccessful && response.body() != null) {
                                        view.hideProgress()
                                        view.onSuccessApplicationSend(response.body()!!)
                                    }
                                    if (response.code() == 400) {
                                        view.hideProgress()
                                        view.onFailureConnnectedWithServer()
                                    }
                                }
                            }

                        }
                )

            }
        }
        else view.onFailureApplicationFilled()
    }

    private fun fillApplication(bodyBuilder: MultipartBody.Builder, activity: AppCompatActivity) {

        val name = StartApplication.sharedPreference.getString(Const.USER_NAME,"null")
        val phone = StartApplication.sharedPreference.getString(Const.USER_PHONE,"null")
        val email = StartApplication.sharedPreference.getString(Const.USER_MAIL,"null")
        bodyBuilder.addFormDataPart("name",name!!)
        bodyBuilder.addFormDataPart("phone",phone!!)
        bodyBuilder.addFormDataPart("email",email!!)
        bodyBuilder.addFormDataPart("device_id",phone)
        applyDocumentsToBodyBuilder(bodyBuilder)
    }

    private var imagePath:String? = null
    private var mPosition:Int = 0
    fun showAlertDialog(position:Int) {
        mPosition = position
        val args = arrayOf("Галерея", "Камера")
        AlertDialog.Builder(activity).setItems(args) { dialog, which ->
            if (which == 1)
                takePhotoFromCamera()
            else
                takePhotoFromGallery()
        }.show()
    }

    fun takePhotoFromCamera() {
        if(Permissions.iPermissionCamera(activity)){
            imagePath = System.nanoTime().toString()
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            val uri = FileUtils.getCaptureImageOutputUri(activity,imagePath)
            if(uri != null){
                val file = File(uri.path)
                if(Build.VERSION.SDK_INT >= 24){
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(activity,
                            BuildConfig.APPLICATION_ID+".provider",file))
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                    intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)

                }
                else intent.putExtra(MediaStore.EXTRA_OUTPUT,uri)
                activity.startActivityForResult(intent,Const.CAMERA)
            }
        }
    }
    fun takePhotoFromGallery(){
        if(Permissions.iPermissionReadStorage(activity)){
            val intent = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/*"
            activity.startActivityForResult(intent,Const.GALLERY)
        }
    }
    fun saveCameraPath(){
        val uri  = FileUtils.getCaptureImageOutputUri(activity,imagePath)
        val file = FileUtils.getNormalizedUri(activity, uri)
        val path = Compressor.getDefault(activity).compressToFile(File(file.path)).path
        imagePath = path

        StartApplication.sharedPreference.edit().putString(mPosition.toString(), imagePath).apply()
        view.onSuccessedSavePath()
    }
    fun saveGalleryPath(data:Intent){
        imagePath = FileUtils.getImagePathFromInputStreamUri(StartApplication.INSTANCE,data.data)
        StartApplication.sharedPreference.edit().putString(mPosition.toString(),imagePath).apply()
        view.onSuccessedSavePath()

    }
    fun isApplicationFilled():Boolean {
        if ((!map.isEmpty() && map.size < 6) || map.isEmpty()) {
            for (i in 0 until 6) {
                val imagePath = StartApplication.sharedPreference.getString(i.toString(), "null")
                if (imagePath == "null")
                    return false
                else
                    map.put(i.toString(), imagePath!!)

            }

        }
        return map.size == 6
    }
    fun saveOwnerIdAndStatus (documentStatus: DocumentStatus) {
        StartApplication.sharedPreference.edit().putInt(Const.OWNER_ID, documentStatus.id).apply()
        StartApplication.sharedPreference.edit().putInt(Const.OWNER_STATUS,documentStatus.status).apply()
    }

    fun isFirstSending():Boolean{
        return StartApplication.sharedPreference.getInt(Const.OWNER_STATUS,0) == 0
    }

    fun isViewAttached(): Boolean = view != null

    fun getApplicationStatus():Int{
        val status = StartApplication.sharedPreference.getInt(Const.OWNER_STATUS,0)
        return status
    }
}
