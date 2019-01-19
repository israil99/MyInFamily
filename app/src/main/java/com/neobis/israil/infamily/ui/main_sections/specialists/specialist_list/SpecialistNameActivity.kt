package com.neobis.israil.infamily.ui.main_sections.specialists.specialist_list

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.neobis.israil.infamily.BaseActivity
import com.neobis.israil.infamily.R
import com.neobis.israil.infamily.model.Specialistest
import com.neobis.israil.infamily.ui.main_sections.specialists.specialist_profile.ProfileActivity
import com.neobis.israil.infamily.utill.Const
import com.neobis.israil.infamily.utill.Const.EXTRA_SPECIALIST_ID
import kotlinx.android.synthetic.main.activity_specialist_name.*

class SpecialistNameActivity: BaseActivity() , SpecialistNameAdapter.Listener {

    private lateinit var adapter: SpecialistNameAdapter
    private lateinit var specialistList: Specialistest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_specialist_name)
        specialistList = intent.getSerializableExtra(Const.SERIALAIZABLE) as Specialistest
        adapter = SpecialistNameAdapter(specialistList.people, this)
        recyclerView.adapter = adapter
    }
    override fun onItemSelectedAt(position: Int) {
        val intent = Intent(this, ProfileActivity::class.java)
        intent.putExtra(EXTRA_SPECIALIST_ID, specialistList.people[position].id)
        startActivity(intent)

    }

}


