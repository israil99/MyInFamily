package com.neobis.israil.infamily.ui.main_sections.about_children.topics

import android.os.Bundle
import android.support.design.widget.Snackbar
import com.neobis.israil.infamily.BaseActivity
import com.neobis.israil.infamily.R
import com.neobis.israil.infamily.model.Category
import kotlinx.android.synthetic.main.activity_topic.*

class TopicActivity : BaseActivity(),TopicContract.View,TopicAdapter.Listener {
    private lateinit var adapter:TopicAdapter
    private lateinit var presenter:TopicPresenter
    private var list: MutableList<Category> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topic)

        init()
    }

    private fun init() {
        presenter = TopicPresenter(this)
        presenter.getTopics(intent.getIntExtra("topic",0))

        adapter = TopicAdapter(list, this)
        recyclerView.adapter = adapter

    }


    override fun onSuccess(result: MutableList<Category>) {
        this.list.addAll(result)
        adapter.setPostList(this.list)
        recyclerView.adapter = adapter
    }

    override fun onError(message: String?) {
        Snackbar.make(rootView,getString(R.string.error_network),Snackbar.LENGTH_SHORT).show()
    }

    override fun onItemSeletedAt(position: Category) {
        //presenter.startActivity(this,position)
    }
    override fun onSectionResponse(result: Category) {
    }

}
