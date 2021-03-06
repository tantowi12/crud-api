package com.tantowi.plugin.webservices

import com.google.gson.annotations.SerializedName
import com.tantowi.plugin.models.Post
import com.tantowi.plugin.models.Theory
import com.tantowi.plugin.models.User
import com.tantowi.plugin.utilities.Util
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

class Api{
    companion object {
        private var retrofit: Retrofit? = null

        private var okHttpClient = OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS).build()

        fun instance() : ApiService = getClient().create(ApiService::class.java)

        private fun getClient(): Retrofit {
            return if (retrofit == null) {
                retrofit = Retrofit.Builder().baseUrl(Util.API_ENDPOINT)
                    .client(okHttpClient).addConverterFactory(
                    GsonConverterFactory.create()).build()
                retrofit!!
            } else {
                retrofit!!
            }
        }
    }
}

//~~//

interface ApiService {

    @FormUrlEncoded
    @POST("auth/sign-in")
    fun login(@Field("username") username: String? = null,
              @Field("password") password: String? = null
    ) : Call<WrapedResponse<User>>

    @GET("squad")
    fun get(@Header("x-access-token") token : String) : Call<WrappedListResponse<Post>>

    @GET("squad/{id}")
    fun find(@Header("x-access-token") token: String, @Path("id") id: String) : Call<WrapedResponse<Post>>

    @FormUrlEncoded
    @POST("squad")
    fun createData(
        @Header("x-access-token") token: String,
        @Field("squads_name") squads_name: String? = null,
        @Field("description") description: String? = null
    ) : Call<WrapedResponse<Post>>

    @FormUrlEncoded
    @PUT("squad/{id}")
    fun updateData(
            @Path("id") id: Int,
            @Header("x-access-token") token: String,
            @Field("squads_name") squads_name: String? = null,
            @Field("description") description: String? = null
    ) :Call<WrapedResponse<Post>>

    @DELETE("squad/{id}")
    fun deleteData(
            @Path("id") id: Int,
            @Header("x-access-token") token: String

    ) : Call<WrapedResponse<Post>>

    // Theory
    @GET("theory")
    fun getTheory(@Header("x-access-token") token: String) : Call<WrappedListResponse<Theory>>
}

//~~//

data class WrapedResponse<T>(
    @SerializedName("message") var message : String?,
    @SerializedName("status") var status : Int?,
    @SerializedName("data") var data: T?
) {
    constructor() : this(null, null, null)
}

data class WrappedListResponse<T>(
    @SerializedName("message") var message: String?,
    @SerializedName("status") var status: Int?,
    @SerializedName("data") var data: List<T>
) {
    constructor() :this(null, null, listOf())
}