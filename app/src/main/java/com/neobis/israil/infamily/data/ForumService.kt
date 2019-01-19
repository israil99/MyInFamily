package com.neobis.israil.infamily.data

import com.neobis.israil.infamily.model.*
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*


interface ForumService {

    @GET("sections/{id}")
    fun getSections(@Path("id") id: Int): Call<MutableList<Section>>

    @GET("categories/{id}")
    fun getTopics(@Path("id") id: Int): Call<MutableList<Category>>

    @POST("documents/")
    fun sendApplication(@Body file: RequestBody): Call<DocumentStatus>

    @GET("documents/{id}/")
    fun checkStatus(@Path("id") id: Int, @Header("DEVICE") deviceId: String): Call<DocumentStatus>

    @GET("people/")
    fun getSpecialistList():Call<List<Specialistest>>

    @GET("people/{id}")
    fun getSpecialistArticle(@Path("id") id:Int): Call<SpecialistProfile>

    @PATCH("documents/{id}/")
    fun updateDocumentStatus(@Body file:RequestBody, @Path("id") id: Int, @Header("DEVICE") deviceId:String): Call<DocumentStatus>

    @POST("devices/")
    fun sendToken(@Body file: RequestBody): Call<TokenInfo>
}
