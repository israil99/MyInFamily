package com.neobis.israil.infamily.ui.main_sections.about_children

import com.neobis.israil.infamily.model.Section
import com.neobis.israil.infamily.utill.ProgBar
import com.neobis.israil.infamily.utill.SuccessOrError

interface AboutChildrenContract {
        interface View : ProgBar, SuccessOrError<MutableList<Section>> {
            fun onSection2Response(result: Section)
        }

        interface Presenter {
            fun loadSections2(id:Int)
        }
    }
