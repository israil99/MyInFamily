package com.neobis.israil.infamily.ui.main_sections.already_parent

import com.neobis.israil.infamily.model.Section
import com.neobis.israil.infamily.utill.ProgBar
import com.neobis.israil.infamily.utill.SuccessOrError

interface AlreadyParentContract {
    interface View : ProgBar, SuccessOrError<MutableList<Section>> {
        fun onSection3Response(result: Section)
    }

    interface Presenter {
        fun loadSections3(id: Int)
    }
}