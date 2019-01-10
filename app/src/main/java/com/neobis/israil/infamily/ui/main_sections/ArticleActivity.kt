package com.neobis.israil.infamily.ui.main_sections

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.neobis.israil.infamily.BaseActivity
import com.neobis.israil.infamily.R
import com.neobis.israil.infamily.model.Category
import kotlinx.android.synthetic.main.activity_article.*

class ArticleActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)
        init()
    }
    private fun init() {
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setDisplayShowHomeEnabled(true)
        initViews()
    }

    private fun initViews() {
        val category:Category= intent.extras.getSerializable("article") as Category
        article_title.text = category.title
        Glide.with(this).load(category.image).into(image_article)
        tvDesciption.text = category.content
    }



}

