package com.neobis.israil.infamily.ui.main_sections.become_parent

import android.content.Intent
import android.util.Log
import com.neobis.israil.infamily.R
import com.neobis.israil.infamily.StartApplication
import com.neobis.israil.infamily.model.DocumentStatus
import com.neobis.israil.infamily.model.Section
import com.neobis.israil.infamily.ui.main_sections.become_parent.information.InformationActivity
import com.neobis.israil.infamily.ui.main_sections.become_parent.quiz.QuizActivity
import com.neobis.israil.infamily.ui.main_sections.become_parent.send_documents.DocumentsActivity
import com.neobis.israil.infamily.ui.main_sections.become_parent.signup.AuthActivity
import com.neobis.israil.infamily.utill.Connection
import com.neobis.israil.infamily.utill.Const
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BecomeParentPresenter(val view: BecomeParentContract.View?,activity: BecomeParentActivity) : BecomeParentContract.Presenter{

    override fun loadSections(id: Int) {
        if (!Connection.isNetworkOnline()) {
            Log.d("Network", "Error")
            view?.onError(StartApplication.INSTANCE.getString(R.string.error_network))
            return
        }
        Log.d("OK", "Connection")
        view?.showProgress()
        StartApplication.service.getSections(id).enqueue(object :Callback<MutableList<Section>>{
            override fun onResponse(call: Call<MutableList<Section>>, response: Response<MutableList<Section>>) {
                if (response!!.isSuccessful && response.body() != null) {
                    val list = response.body()
                    Log.d("Success",list!!.size.toString())
                    view?.onSuccess(list)
                } else {
                    Log.d("Error","Zapros ne proshel")
                    Log.d("presenter","30")
                    view?.onError(StartApplication.INSTANCE.getString(R.string.error_response))
                }
                view?.hideProgress()
            }

            override fun onFailure(call: Call<MutableList<Section>>, t: Throwable) {
                view?.onError(StartApplication.INSTANCE.getString(R.string.error_response))
                Log.d("presenter",t.cause.toString())
                view?.hideProgress()
            }

        })
    }



    private val mActivity = activity
    fun startActivity(position:Int) {
        var activity: Class<*>? = null
        when (position) {
            1 -> {
                if(StartApplication.sharedPreference.getInt(Const.OWNER_STATUS,0) != 0){
                    view!!.onAlreadyApplicationSend()
                    return
                }
                else if (StartApplication.sharedPreference.getBoolean(Const.IS_AUTH, false) == false)
                    activity = AuthActivity::class.java
                else {
                    activity = DocumentsActivity::class.java
                }
            }

            2 -> activity = QuizActivity::class.java
            0-> activity = InformationActivity::class.java
        }
        mActivity.startActivity(Intent(mActivity, activity))

    }

    override fun checkApplicaitonStatus() {
        if(isViewAttached()){
            val id = StartApplication.sharedPreference.getInt(Const.OWNER_ID,0)
            val device = StartApplication.sharedPreference.getString(Const.USER_PHONE,null)
            StartApplication.service.checkStatus(id,device).enqueue(
                    object: Callback<DocumentStatus> {
                        override fun onFailure(call: Call<DocumentStatus>?, t: Throwable?) {
                            view!!.hideProgress()
                            view!!.onFailureConnnectedWithServer()
                        }

                        override fun onResponse(call: Call<DocumentStatus>?, response: Response<DocumentStatus>?) {
                            view!!.hideProgress()
                            view!!.onSuccessApplicationStatusChecked(response?.body()!!)
                        }

                    })
        }
    }
    fun determineServerStatus(status:Int):String{
        var serverStatus = ""
        when(status){
            1-> serverStatus = mActivity.getString(R.string.status1)
            2->serverStatus = mActivity.getString(R.string.status1)
            3->serverStatus = mActivity.getString(R.string.status3)
            4->serverStatus = mActivity.getString(R.string.status4)
            5->serverStatus = mActivity.getString(R.string.status5)
        }
        return serverStatus
    }
    fun updateApplicationDocuments(documentStatus: DocumentStatus){
        if(!documentStatus.family_correct)
            StartApplication.sharedPreference.edit().remove("0").apply()
        if(!documentStatus.income_correct)
            StartApplication.sharedPreference.edit().remove("1").apply()
        if(!documentStatus.residence_correct)
            StartApplication.sharedPreference.edit().remove("2").apply()
        if(!documentStatus.criminal_correct)
            StartApplication.sharedPreference.edit().remove("3").apply()
        if(!documentStatus.health_correct)
            StartApplication.sharedPreference.edit().remove("4").apply()
        if(!documentStatus.job_correct)
            StartApplication.sharedPreference.edit().remove("5").apply()
    }
    fun determineStatus(documentStatus: DocumentStatus):String{
        val serverStatus =  determineServerStatus(documentStatus.status)
        (serverStatus == mActivity.getString(R.string.status4))
        if(serverStatus == mActivity.getString(R.string.status4))
            updateApplicationDocuments(documentStatus)
        return serverStatus
    }
    fun isViewAttached():Boolean = view != null
}