package com.neobis.israil.infamily.ui.main_sections.become_parent

import com.neobis.israil.infamily.model.DocumentStatus
import com.neobis.israil.infamily.model.Section
import com.neobis.israil.infamily.utill.ProgBar
import com.neobis.israil.infamily.utill.SuccessOrError

interface BecomeParentContract {
    interface View:ProgBar,SuccessOrError<MutableList<Section>>{
        fun onAlreadyApplicationSend()
        fun onFailureConnnectedWithServer()
        fun onSuccessApplicationStatusChecked(documentStatus: DocumentStatus)

    }
    interface Presenter{
        fun checkApplicaitonStatus()
        fun loadSections(id:Int)
    }

}
