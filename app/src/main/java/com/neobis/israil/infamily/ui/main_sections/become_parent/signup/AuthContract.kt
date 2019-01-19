package com.neobis.israil.infamily.ui.main_sections.become_parent.signup

import com.neobis.israil.infamily.utill.ProgBar

interface AuthContract {
    interface View:ProgBar{
        fun  onSuccessCheckFields()
        fun onIncorrectName()
        fun onIncorrectEmail()
        fun onIncorrectPhone()
        fun onSuccessUserDataSaved()
        fun onSuccessTokenSending()
        fun onFailureTokenSending()
    }

    interface Presenter{
        fun checkInputFields(name:String,email:String,phone:String)
        fun saveUserData(name:String,mail:String,phone:String)
        fun sendFirebaseToken()
    }
}