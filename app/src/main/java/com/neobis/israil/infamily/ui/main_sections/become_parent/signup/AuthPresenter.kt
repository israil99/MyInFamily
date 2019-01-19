package com.neobis.israil.infamily.ui.main_sections.become_parent.signup

import android.text.TextUtils
import android.util.Log
import com.neobis.israil.infamily.StartApplication
import com.neobis.israil.infamily.model.TokenInfo
import com.neobis.israil.infamily.utill.Const
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthPresenter(val view:AuthContract.View?):AuthContract.Presenter {


    override fun checkInputFields(name:String,email:String,phone:String) {
        if (TextUtils.isEmpty(name) || name.length < 4)
            view?.onIncorrectName()
        if (TextUtils.isEmpty(email) || !email.contains('@'))
            view?.onIncorrectEmail()
        if (TextUtils.isEmpty(phone) || phone.length < 9)
            view?.onIncorrectPhone()
        else
            view?.onSuccessCheckFields()

    }

    override fun saveUserData(name: String, mail: String, phone: String) {
        StartApplication.sharedPreference.edit().putString(Const.USER_NAME,name)
                .putString(Const.USER_MAIL,mail).putString(Const.USER_PHONE,phone).putBoolean(Const.IS_AUTH,true).apply()
        view?.onSuccessUserDataSaved()
    }
    override fun sendFirebaseToken() {
        val bodyBuilder = getMultipartBody()
        Log.d("Token2",bodyBuilder.toString())
        view?.showProgress()
        StartApplication.service.sendToken(bodyBuilder.build()).enqueue(object:
                Callback<TokenInfo> {
            override fun onFailure(call: Call<TokenInfo>?, t: Throwable?) {
                Log.d("ERROR",t.toString())
                if(isViewAttached())
                    view?.onFailureTokenSending()
                view?.hideProgress()
            }

            override fun onResponse(call: Call<TokenInfo>?, response: Response<TokenInfo>?) {
                if(isViewAttached()){
                    Log.d("ERROR",response.toString())
                    if(response!!.isSuccessful && response.body() != null){
                        Log.d("success",response.message())
                        view?.hideProgress()
                        view?.onSuccessTokenSending()
                    }
                }
            }

        })


    }

    private fun getMultipartBody(): MultipartBody.Builder {
        val body = MultipartBody.Builder()
        Log.d("token"+StartApplication.sharedPreference.getString(Const.FIREBASE_TOKEN,"null"),"info")
        body.addFormDataPart("registration_id",StartApplication.sharedPreference.getString(Const.FIREBASE_TOKEN,"null"))
        body.addFormDataPart("device_id",StartApplication.sharedPreference.getString(Const.USER_PHONE,"null"))
        body.addFormDataPart("type","android")
        body.addFormDataPart("active","true")
        body.setType(MultipartBody.FORM)

        return body


    }
    private fun isViewAttached():Boolean = view != null


}