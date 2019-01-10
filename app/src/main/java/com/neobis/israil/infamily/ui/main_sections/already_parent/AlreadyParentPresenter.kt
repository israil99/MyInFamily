package com.neobis.israil.infamily.ui.main_sections.already_parent

import android.content.Context
import android.content.Intent
import android.util.Log
import com.neobis.israil.infamily.R
import com.neobis.israil.infamily.StartApplication
import com.neobis.israil.infamily.model.Section
import com.neobis.israil.infamily.ui.main_sections.topics.TopicActivity
import com.neobis.israil.infamily.utill.Connection
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AlreadyParentPresenter (val view: AlreadyParentContract.View?) : AlreadyParentContract.Presenter{
    override fun loadSections3(id: Int) {
        if (!Connection.isNetworkOnline()) {
            Log.d("Network", "Error")
            view?.onError(StartApplication.INSTANCE.getString(R.string.error_network))
            return
        }
        Log.d("OK", "Connection")
        view?.showProgress()
        StartApplication.service.getSections(id).enqueue(object : Callback<MutableList<Section>> {
            override fun onResponse(call: Call<MutableList<Section>>, response: Response<MutableList<Section>>) {
                if (response!!.isSuccessful && response.body() != null) {
                    val list = response.body()
                    Log.d("Success",list!!.size.toString())
                    view?.onSuccess(list)
                } else {
                    Log.d("Error","Zapros ne proshel")
                    Log.d("presenter","30")
                    view?.onError(StartApplication.INSTANCE.getString(R.string.error_response))
                }
                view?.hideProgress()
            }

            override fun onFailure(call: Call<MutableList<Section>>, t: Throwable) {
                view?.onError(StartApplication.INSTANCE.getString(R.string.error_response))
                Log.d("presenter",t.cause.toString())
                view?.hideProgress()
            }

        })
    }
    fun startActivity(context: Context, position:Int){
        val intent = Intent(context, TopicActivity::class.java)
        var timelineId:Int = 0
        when(position){
            0 -> timelineId = 7
            1 -> timelineId = 9
            2 -> timelineId = 20

        }
        intent.putExtra("topic",timelineId)
        context.startActivity(intent)

    }


}