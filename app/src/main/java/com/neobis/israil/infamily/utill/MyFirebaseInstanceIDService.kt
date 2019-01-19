package com.neobis.israil.infamily.utill


import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService
import com.neobis.israil.infamily.StartApplication

@Suppress("OverridingDeprecatedMember", "DEPRECATION")
class MyFirebaseInstanceIDService : FirebaseInstanceIdService(){
    override fun onTokenRefresh() {
        super.onTokenRefresh()

        try {
            val refreshedToken = FirebaseInstanceId.getInstance().token
            Log.d("Firebase id login", "Refreshed token: " + refreshedToken!!)

            FileUtils.writeCacheData(this, Const.REFRESHED_TOKEN_FOR_FIREBASE,refreshedToken)
            StartApplication.sharedPreference.edit().putString(Const.FIREBASE_TOKEN,refreshedToken).apply()

        } catch (e: Exception) {
            e.printStackTrace()
        }




    }





}