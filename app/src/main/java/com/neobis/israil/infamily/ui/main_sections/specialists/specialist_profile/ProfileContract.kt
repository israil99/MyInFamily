package com.neobis.israil.infamily.ui.main_sections.specialists.specialist_profile

import com.neobis.israil.infamily.model.SpecialistProfile
import com.neobis.israil.infamily.utill.ProgBar

interface ProfileContract {
    interface View : ProgBar {
        fun onSuccessGetProfile(specialistArticle: SpecialistProfile)
        fun onFailureGetProfile()
    }
    interface Presenter{
        fun getSpecialistProfileById(id:Int)
    }
}