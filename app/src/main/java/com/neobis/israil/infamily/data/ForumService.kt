package com.neobis.israil.infamily.data

import com.neobis.israil.infamily.model.Category
import com.neobis.israil.infamily.model.Section
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface ForumService {

    @GET("sections/{id}")
    fun getSections(@Path("id") id: Int): Call<MutableList<Section>>

    @GET("categories/{id}")
    fun getTopics(@Path("id") id: Int): Call<MutableList<Category>>
}