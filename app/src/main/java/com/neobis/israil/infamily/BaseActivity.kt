package com.neobis.israil.infamily

import android.app.ProgressDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.Toast
@Suppress("DEPRECATION")
open class  BaseActivity: AppCompatActivity(){
    private var progressBar: ProgressDialog? = null


    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        setupBackButton()
    }

    private fun setupBackButton() {
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()?.setDisplayShowHomeEnabled(true);
    }

  /*  override fun onResume() {
        super.onResume()
        if(!ConnectionManager.isNetworkAvailable(this))
            Toast.makeText(this,"No Internet Connection",Toast.LENGTH_SHORT).show()
    }*/

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(0,0);
    }

    open fun showProgress(){
        this.runOnUiThread{
            progressBar = null
            if(progressBar == null && !isFinishing){
                progressBar = ProgressDialog(this)
                progressBar?.setMessage("Loading")
                progressBar?.setCanceledOnTouchOutside(false)
                progressBar?.show()
            }
        }
    }
    open fun hideProgress(){
        this.runOnUiThread{
            if(progressBar != null && progressBar?.isShowing!!){
                progressBar!!.dismiss()
                progressBar = null
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        hideProgress()
    }
}