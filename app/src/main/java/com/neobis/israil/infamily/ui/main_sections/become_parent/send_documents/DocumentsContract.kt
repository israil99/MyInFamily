package com.neobis.israil.infamily.ui.main_sections.become_parent.send_documents

import com.neobis.israil.infamily.model.DocumentStatus
import com.neobis.israil.infamily.utill.ProgBar

interface DocumentsContract {
    interface View: ProgBar {
        fun onSuccessApplicationSend(documentStatus: DocumentStatus)
        fun onSuccessedSavePath()
        fun onSuccessApplicationUpdated()
        fun onFailureConnnectedWithServer()
        fun onFailureApplicationFilled()

    }
    interface Presenter{
        fun sendApplciation()
        fun updateApplcication()

    }
}