package com.neobis.israil.infamily.ui.main_sections

import android.os.Bundle
import com.neobis.israil.infamily.BaseActivity
import com.neobis.israil.infamily.R
import com.neobis.israil.infamily.data.Data
import com.neobis.israil.infamily.model.MainSection
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: BaseActivity() , MainAdapter.Listener {
private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
        initClickListeners()
        setupActionBar()

    }

    private fun setupActionBar() {
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(false)
        getSupportActionBar()?.setDisplayShowHomeEnabled(false)
    }

    private fun initClickListeners() {

    }

    private fun initRecyclerView() {
        adapter = MainAdapter(getMainCategoriesList(),this,this)
        recyclerView.adapter = adapter
    }

    private fun getMainCategoriesList(): List<MainSection> {
        return   Data.getMainCategories(this)

    }
/*
    override fun onItemSelectedAt(position: Int) {
        if(position ==0){
        }
    }*/
}
