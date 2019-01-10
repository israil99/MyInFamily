package com.neobis.israil.infamily.ui.main_sections.topics

import android.app.ActivityOptions
import android.content.Intent
import android.util.Log
import com.neobis.israil.infamily.R
import com.neobis.israil.infamily.StartApplication
import com.neobis.israil.infamily.model.Category
import com.neobis.israil.infamily.ui.main_sections.ArticleActivity
import com.neobis.israil.infamily.utill.Connection
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TopicPresenter(val view :TopicContract.View):TopicContract.Presenter{
    override fun getTopics(id: Int) {
        if (!Connection.isNetworkOnline()) {
                    Log.d("Network", "Error")
                    view?.onError(StartApplication.INSTANCE.getString(R.string.error_network))
                    return
                }
                Log.d("OK", "Connection")
        view?.showProgress()
        StartApplication.service.getTopics(id).enqueue(object :Callback<MutableList<Category>>{
            override fun onFailure(call: Call<MutableList<Category>>, t: Throwable) {
                view?.onError(StartApplication.INSTANCE.getString(R.string.error_response))
                Log.d("presenter",t.cause.toString())
                view?.hideProgress()
            }

            override fun onResponse(call: Call<MutableList<Category>>, response: Response<MutableList<Category>>) {
                if (response!!.isSuccessful && response.body() != null) {
                    val list = response.body()
                    Log.d("Success",list!!.size.toString())
                    view.onSuccess(list)
                } else {
                    Log.d("Error","Zapros ne proshel")
                    Log.d("presenter","30")
                    view.onError(StartApplication.INSTANCE.getString(R.string.error_response))
                }
                view.hideProgress()
            }

        })



    }
    fun startActivity(activity: TopicActivity,category:Category){
        val intent = Intent(activity, ArticleActivity::class.java)
        intent.putExtra("article",category)

        val options = ActivityOptions.makeCustomAnimation(activity, R.anim.abc_slide_in_bottom,R.anim.abc_slide_out_bottom)
        activity.startActivity(intent,options.toBundle())

    }
}