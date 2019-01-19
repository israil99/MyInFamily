package com.neobis.israil.infamily

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.neobis.israil.infamily.data.ForumService
import com.neobis.israil.infamily.data.Network
import com.neobis.israil.infamily.utill.Const
import com.neobis.israil.infamily.utill.Helper

class StartApplication : Application() {
    companion object {
        @Volatile
        lateinit var INSTANCE: StartApplication
        lateinit var service: ForumService
        lateinit var sharedPreference: SharedPreferences
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        service = Network.initService(Helper.BASE_URL)
        sharedPreference = getSharedPreferences(Const.SHARED_PREFS, Context.MODE_PRIVATE)
    }

}