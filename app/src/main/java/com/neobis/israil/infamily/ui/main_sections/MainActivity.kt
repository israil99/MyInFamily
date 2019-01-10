package com.neobis.israil.infamily.ui.main_sections

import android.content.Intent
import android.os.Bundle
import com.neobis.israil.infamily.BaseActivity
import com.neobis.israil.infamily.R
import com.neobis.israil.infamily.data.Data
import com.neobis.israil.infamily.model.MainSection
import com.neobis.israil.infamily.ui.main_sections.about_children.AboutChildrenActivity
import com.neobis.israil.infamily.ui.main_sections.already_parent.AlreadyParentActivity
import com.neobis.israil.infamily.ui.main_sections.become_parent.BecomeParentActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: BaseActivity() , MainAdapter.Listener {
private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        initRecyclerView()
        initTitles()
        initClickListeners()
        setupActionBar()

    }

    private fun setupActionBar() {
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(false)
        getSupportActionBar()?.setDisplayShowHomeEnabled(false)
    }

    private fun initClickListeners() {
        first_main_card.setOnClickListener {
            intent = Intent(this, BecomeParentActivity::class.java)
            startActivity(intent)
        }
        second_main_card.setOnClickListener {
            intent = Intent(this, AboutChildrenActivity::class.java)
            startActivity(intent)
        }
        third_main_card.setOnClickListener {
             intent = Intent(this, AlreadyParentActivity::class.java)
             startActivity(intent)
        }

    }


    private fun initTitles(){
        first_main_tv.text = applicationContext.getText(R.string.main_category1)
        second_main_tv.text = applicationContext.getText(R.string.main_category2)
        third_main_tv.text = applicationContext.getText(R.string.main_category3)
        fourth_main_tv.text = applicationContext.getText(R.string.main_category4)
    }
}
