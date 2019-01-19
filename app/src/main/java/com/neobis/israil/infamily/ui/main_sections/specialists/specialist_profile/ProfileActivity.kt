package com.neobis.israil.infamily.ui.main_sections.specialists.specialist_profile

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import com.bumptech.glide.Glide
import com.neobis.israil.infamily.BaseActivity
import com.neobis.israil.infamily.R
import com.neobis.israil.infamily.model.SpecialistProfile
import com.neobis.israil.infamily.utill.Const
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity: BaseActivity(),ProfileContract.View{

    private lateinit var presenter: ProfilePresenter
    private lateinit var userProfile: SpecialistProfile

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        init()

    }
    private fun init(){
        initPresenter()
        initListeners()
    }

    private fun initListeners() {

    }

    private fun initPresenter() {
        presenter = ProfilePresenter(this)
        presenter.getSpecialistProfileById(intent.getIntExtra(Const.EXTRA_SPECIALIST_ID,1))

    }

    override fun onSuccessGetProfile(specialistArticle: SpecialistProfile) {
        userProfile = specialistArticle
        initView()
    }

    private fun initView() {
        Glide.with(rootView).load(userProfile.photo).into(imgProfile)
        tvAddress.text = userProfile.address
        tvName.text = userProfile.name
        tvSchedule.text = userProfile.schedule
        if(userProfile.contacts!!.size >0)
            tvPhone.text = userProfile.contacts?.get(0)!!.value
        tvLocation.text = getString(R.string.show_on_map)
    }


    override fun onFailureGetProfile() {
        Snackbar.make(rootView,getString(R.string.no_internet),Snackbar.LENGTH_SHORT).show()
    }

}
