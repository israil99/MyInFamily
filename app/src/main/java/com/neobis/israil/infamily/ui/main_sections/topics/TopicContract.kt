package com.neobis.israil.infamily.ui.main_sections.topics

import com.neobis.israil.infamily.model.Category
import com.neobis.israil.infamily.utill.ProgBar
import com.neobis.israil.infamily.utill.SuccessOrError

interface TopicContract {
    interface View : ProgBar, SuccessOrError<MutableList<Category>> {
        fun onSectionResponse(result: Category)
    }
    interface Presenter{
        fun getTopics(id:Int)
    }
}