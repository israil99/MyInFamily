package com.neobis.israil.infamily.data

import android.content.Context
import com.neobis.israil.infamily.R
import com.neobis.israil.infamily.model.MainSection

object Data {

    fun getMainCategories(context: Context): MutableList<MainSection> {
        val array: MutableList<MainSection> = mutableListOf()
        array.add(MainSection(context.getString(R.string.main_category1), R.drawable.family))
        array.add(MainSection(context.getString(R.string.main_category2), R.drawable.couple))
        array.add(MainSection(context.getString(R.string.main_category3), R.drawable.son))
        array.add(MainSection(context.getString(R.string.main_category4), R.drawable.doctor))
        return array
    }
}