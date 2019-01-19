package com.neobis.israil.infamily.ui.main_sections.specialists

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.neobis.israil.infamily.BaseActivity
import com.neobis.israil.infamily.R
import com.neobis.israil.infamily.model.Specialistest
import com.neobis.israil.infamily.ui.main_sections.specialists.specialist_list.SpecialistNameActivity
import com.neobis.israil.infamily.utill.Const
import kotlinx.android.synthetic.main.activity_specialist.*

class SpecialistActivity : BaseActivity(),SpecialistContract.View,SpecialistAdapter.Listener {

    private lateinit var adapter: SpecialistAdapter
    private lateinit var presenter: SpecialistPresenter
    private lateinit var mList: List<Specialistest>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_specialist)



        init()
    }

    private fun init() {
        initPresenter()
    }

    private fun initPresenter() {

        presenter = SpecialistPresenter(this)
        presenter.getSpecialistList()
    }

    override fun onSuccessGetSpecialistList(list: List<Specialistest>) {
        mList = list
        initAdapter(list)
    }

    private fun initAdapter(list:List<Specialistest>) {
        val imageList = getImageList()
        adapter = SpecialistAdapter(list, this,imageList)
        recyclerView.adapter = adapter
    }

    override fun onFailureGetSpecialistList() {
    }

    override fun onItemSelectedAt(position: Int) {
        val intent = Intent(this, SpecialistNameActivity::class.java)
        intent.putExtra(Const.SERIALAIZABLE, mList[position])
        startActivity(intent)

    }
    private fun getImageList(): ArrayList<Int> {
        val list: ArrayList<Int> = ArrayList()
        list.add(R.drawable.teacher)
        list.add(R.drawable.doctor)
        list.add(R.drawable.disc_jockey)
        list.add(R.drawable.businessman)

        return list
    }




}