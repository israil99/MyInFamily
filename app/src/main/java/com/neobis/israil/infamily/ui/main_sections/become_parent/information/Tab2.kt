package com.neobis.israil.infamily.ui.main_sections.become_parent.information

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.neobis.israil.infamily.R
import kotlinx.android.synthetic.main.tab1_fragment.*


class Tab2: Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.tab1_fragment,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textView.text = getString(R.string.tab_text2)
        tvTitle.text = getString(R.string.tab_title_2)
    }
}