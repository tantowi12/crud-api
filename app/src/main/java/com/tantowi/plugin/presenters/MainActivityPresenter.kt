package com.tantowi.plugin.presenters

import android.util.Log
import com.tantowi.plugin.contracts.MainActivityContract
import com.tantowi.plugin.models.Post
import com.tantowi.plugin.models.User
import com.tantowi.plugin.webservices.Api
import com.tantowi.plugin.webservices.WrappedListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityPresenter(v : MainActivityContract.MainActivityView?): MainActivityContract.Interaction{

    private var view : MainActivityContract.MainActivityView?= v
    private var api = Api.instance()

    override fun allUser(token: String) {

        val request = api.get("Bearer "+token)
        request.enqueue(object :Callback<WrappedListResponse<Post>>{

            override fun onFailure(call: Call<WrappedListResponse<Post>>, t: Throwable) {
                println("Log: ${t.message}")
                Log.d("tes", "Tes Log")
            }

            override fun onResponse(
                call: Call<WrappedListResponse<Post>>,
                response: Response<WrappedListResponse<Post>>
            ) {

                if (response.isSuccessful) {
                    val body = response.body()
                    println("response " + response.body())
                    if (body != null && body.status !== null) {
                        view?.attachToRecycle(body.data)
                        println("body " + body.data)
                    } else {
                        view?.toast("ada yang salah, coba lai nanti")
                    }
                }
            }

        })
    }

    override fun destroy() {
        view = null
    }

}