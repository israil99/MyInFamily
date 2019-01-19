package com.neobis.israil.infamily.ui.main_sections.become_parent.send_documents

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.neobis.israil.infamily.R

import android.app.Activity
import android.content.Intent
import android.support.design.widget.Snackbar
import com.neobis.israil.infamily.BaseActivity
import com.neobis.israil.infamily.model.DocumentStatus
import com.neobis.israil.infamily.utill.Const
import com.neobis.israil.infamily.utill.Permissions

import kotlinx.android.synthetic.main.activity_documents.*

class DocumentsActivity : BaseActivity() ,DocumentsAdapter.Listener, DocumentsContract.View{


    lateinit var presenter:DocumentsPresenter
    private lateinit var adapter:DocumentsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_documents)

        init()
    }

    private fun init() {
        presenter = DocumentsPresenter(this,this)
        initAdapter()
        initListeners()
    }


    private fun initAdapter() {
        adapter = DocumentsAdapter(this,this)
        recyclerView.adapter = adapter
    }
    private fun initListeners() {
        if(presenter.isFirstSending())
            btn_send.setOnClickListener {
                presenter.sendApplciation()
            }
        else{
            btn_send.setOnClickListener {
                presenter.updateApplcication()
            }
            btn_send.text = getString(R.string.update)
        }
    }

    override fun onItemSelectedAt(positon: Int) {
        presenter.showAlertDialog(positon)

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            when(requestCode){
                Const.CAMERA->  presenter.saveCameraPath()
                Const.GALLERY -> presenter.saveGalleryPath(data!!)

            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if(Permissions.iPermissionCamera(this))
            presenter.takePhotoFromCamera()
        if(Permissions.iPermissionReadStorage(this))
            presenter.takePhotoFromGallery()
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
    override fun onSuccessApplicationSend(documentStatus: DocumentStatus) {
        presenter.saveOwnerIdAndStatus(documentStatus)
        Snackbar.make(rootview,getString(R.string.success_application),Snackbar.LENGTH_LONG)
                .setAction(getString(R.string.ok),{finish()}).show()


    }
    override fun onSuccessedSavePath() {
        adapter.notifyDataSetChanged()

    }
    override fun onSuccessApplicationUpdated() {
        Snackbar.make(rootview,getString(R.string.success_application),Snackbar.LENGTH_LONG)
                .setAction(getString(R.string.success_update),{finish()}).show()
    }


    override fun onFailureConnnectedWithServer() {
        Snackbar.make(rootview,getString(R.string.failure_application),Snackbar.LENGTH_LONG)
                .setAction(getString(R.string.retry),{presenter.sendApplciation()}).show()
    }
    override fun onFailureApplicationFilled() {
        Snackbar.make(rootview,getString(R.string.fill),Snackbar.LENGTH_SHORT).show()
    }



}
