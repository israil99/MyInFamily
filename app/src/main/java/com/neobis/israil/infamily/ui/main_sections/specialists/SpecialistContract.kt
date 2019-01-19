package com.neobis.israil.infamily.ui.main_sections.specialists

import com.neobis.israil.infamily.model.Specialistest
import com.neobis.israil.infamily.utill.ProgBar


interface SpecialistContract {
    interface View: ProgBar {
        fun onSuccessGetSpecialistList(list :List<Specialistest>)
        fun onFailureGetSpecialistList()

    }

    interface Presenter{
        fun getSpecialistList()
    }
}
