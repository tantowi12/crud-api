package com.tantowi.plugin.presenters.theory

import android.util.Log
import com.tantowi.plugin.contracts.TheoryActivityContract
import com.tantowi.plugin.models.Theory
import com.tantowi.plugin.webservices.Api
import com.tantowi.plugin.webservices.WrappedListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class GetTheoryActivityPresenter (v : TheoryActivityContract.TheoryActivityView?) : TheoryActivityContract.Interaction{
    private var view : TheoryActivityContract.TheoryActivityView? = v
    private var api = Api.instance()
    override fun allTheory(token: String) {
        val request = api.getTheory("Bearer " + token)
        println("TOKEN " + token)
        request.enqueue(object : Callback<WrappedListResponse<Theory>> {
            override fun onFailure(call: Call<WrappedListResponse<Theory>>, t: Throwable) {
                println("Log ${t.message}")
                Log.d("tes", "Tes Log")
            }

            override fun onResponse(call: Call<WrappedListResponse<Theory>>, response: Response<WrappedListResponse<Theory>>) {
                if(response.isSuccessful){
                    val body = response.body()
                    println(body)
                    if(body !== null && body.status !== null){
                        view?.attachToRecycle(body.data);
                    }else{
                        view?.toast("Ada yang salah, coba lagi nanti")
                    }
                }
            }

        })
    }

    override fun destroy() {
        view = null
    }


}

