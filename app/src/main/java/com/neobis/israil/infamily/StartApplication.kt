package com.neobis.israil.infamily

import android.app.Application
import com.neobis.israil.infamily.data.ForumService
import com.neobis.israil.infamily.data.Network
import com.neobis.israil.infamily.utill.Helper

class StartApplication : Application() {
    companion object {
        @Volatile
        lateinit var INSTANCE: StartApplication
        lateinit var service: ForumService
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        service = Network.initService(Helper.BASE_URL)
    }

}