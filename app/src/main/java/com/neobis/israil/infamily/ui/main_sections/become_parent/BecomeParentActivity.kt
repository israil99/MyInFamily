package com.neobis.israil.infamily.ui.main_sections.become_parent

import android.os.Bundle
import android.util.Log
import com.neobis.israil.infamily.BaseActivity
import com.neobis.israil.infamily.R
import com.neobis.israil.infamily.model.Section
import kotlinx.android.synthetic.main.activity_become_parent.*

class BecomeParentActivity : BaseActivity(),BecomeParentAdapter.Listener ,BecomeParentContract.View{


    private lateinit var adapter :BecomeParentAdapter
    private lateinit var presenter:BecomeParentPresenter
    private var list: MutableList<Section> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_become_parent)

        init()
    }




    private fun init() {
        presenter = BecomeParentPresenter(this)
        presenter.loadSections(3)

        adapter = BecomeParentAdapter(list, this,this)
        recyclerView2.adapter = adapter
    }



    override fun onSectionResponse(result: Section) {
    }

    override fun onSuccess(result: MutableList<Section>) {
        this.list.addAll(result)
        adapter?.setPostList(this.list)
        recyclerView2.adapter = adapter
    }

    override fun onError(message: String?) {
        Log.d("Error",message)
    }

}
