package com.neobis.israil.infamily.ui.main_sections.about_children

import android.os.Bundle
import android.util.Log
import com.neobis.israil.infamily.BaseActivity
import com.neobis.israil.infamily.R
import com.neobis.israil.infamily.model.Section
import kotlinx.android.synthetic.main.activity_about_children.*

class AboutChildrenActivity : BaseActivity(),AboutChildrenAdapter.Listener , AboutChildrenContract.View{


    private lateinit var adapter :AboutChildrenAdapter
    private lateinit var presenter:AboutChildrenPresenter
    private var list: MutableList<Section> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_children)
        init()
    }

    private fun init() {
        presenter = AboutChildrenPresenter(this)
        presenter.loadSections2(1)

        adapter = AboutChildrenAdapter(list, this)
        recyclerView3.adapter = adapter
    }


    override fun onSection2Response(result: Section) {
    }

    override fun onSuccess(result: MutableList<Section>) {
        this.list.addAll(result)
        adapter.setPostList(this.list)
        recyclerView3.adapter = adapter
    }

    override fun onError(message: String?) {
        Log.d("Error",message)

    }

    override fun onItemSelectedAt(index: Int) {
        presenter.startActivity(this,index)
    }


}
