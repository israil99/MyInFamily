package com.neobis.israil.infamily.ui.main_sections.specialists

import com.neobis.israil.infamily.StartApplication
import com.neobis.israil.infamily.model.Specialistest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SpecialistPresenter(val view : SpecialistContract.View?) : SpecialistContract.Presenter {


        override fun getSpecialistList(){
            if(isViewAttached()){
                view?.showProgress()
                StartApplication.service.getSpecialistList().enqueue(object: Callback<List<Specialistest>> {
                    override fun onResponse(call: Call<List<Specialistest>>?, response: Response<List<Specialistest>>?) {
                        if(isViewAttached()){
                            if(response!!.isSuccessful && response.body() != null){
                                view!!.onSuccessGetSpecialistList(response.body()!!)
                            }
                            else
                                view?.onFailureGetSpecialistList()

                            view!!.hideProgress()
                        }
                    }

                    override fun onFailure(call: Call<List<Specialistest>>?, t: Throwable?) {
                        if(isViewAttached()){
                            view!!.onFailureGetSpecialistList()
                            view.hideProgress()
                        }
                        t?.printStackTrace()
                    }
                })
            }

        }
        private fun isViewAttached(): Boolean = view != null
}