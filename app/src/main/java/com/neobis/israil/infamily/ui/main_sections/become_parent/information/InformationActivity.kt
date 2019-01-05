package com.neobis.israil.infamily.ui.main_sections.become_parent.information

import android.os.Bundle
import android.support.design.widget.TabLayout
import com.neobis.israil.infamily.BaseActivity
import com.neobis.israil.infamily.R
import kotlinx.android.synthetic.main.activity_information.*

class InformationActivity : BaseActivity() {
    private var mSectionsPagerAdapter: ViewPagerAdapter?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information)

        mSectionsPagerAdapter = ViewPagerAdapter(supportFragmentManager)

        // Set up the ViewPager with the sections adapter.
        view_pager_id.adapter = mSectionsPagerAdapter
        view_pager_id.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout_id))
        tabLayout_id.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(view_pager_id))
    }


}
