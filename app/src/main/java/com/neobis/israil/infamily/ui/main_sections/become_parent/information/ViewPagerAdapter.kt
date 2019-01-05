package com.neobis.israil.infamily.ui.main_sections.become_parent.information

import android.app.FragmentManager
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter

class ViewPagerAdapter(fm:android.support.v4.app.FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment? {
        when(position) {
            0 -> {
                return Tab1()
            }
            1 -> {
                return Tab2()
            }
            2 -> {
                return Tab3()
            }
            else ->

                return null

        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {

        when(position){
            0-> return "ФОСТЕРНОЕ ОПЕКУНСТВО"
            1-> return "УСЫНОВЛЕНИЕ"
            2-> return "ОПЕКУНСТВО И ПОПЕЧИТЕЛЬСТВО"

        }
        return null

    }
}