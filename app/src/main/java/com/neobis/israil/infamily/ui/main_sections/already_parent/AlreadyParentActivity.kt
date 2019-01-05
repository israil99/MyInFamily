package com.neobis.israil.infamily.ui.main_sections.already_parent

import android.os.Bundle
import android.util.Log
import com.neobis.israil.infamily.BaseActivity
import com.neobis.israil.infamily.R
import com.neobis.israil.infamily.model.Section
import kotlinx.android.synthetic.main.activity_already_parent.*

class AlreadyParentActivity : BaseActivity(),AlreadyParentAdapter.Listener,AlreadyParentContract.View{

    private lateinit var adapter :AlreadyParentAdapter
    private lateinit var presenter:AlreadyParentPresenter
    private var list: MutableList<Section> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_already_parent)
        init()
    }

    private fun init() {
        presenter = AlreadyParentPresenter(this)
        presenter.loadSections3(2)

        adapter = AlreadyParentAdapter(list, this)
        recyclerView4.adapter = adapter
    }



    override fun onSection3Response(result: Section) {
    }



    override fun onSuccess(result: MutableList<Section>) {
        this.list.addAll(result)
        adapter?.setPostList(this.list)
        recyclerView4.adapter = adapter
    }

    override fun onError(message: String?) {
        Log.d("Error",message)
    }

}

