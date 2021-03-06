package com.tantowi.plugin.presenters

import com.tantowi.plugin.contracts.MainActivityContract
import com.tantowi.plugin.models.Post
import com.tantowi.plugin.models.User
import com.tantowi.plugin.webservices.Api
import com.tantowi.plugin.webservices.WrapedResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateActivityPresenter (v: MainActivityContract.ViewCreate) : MainActivityContract.InteractionPost {

    private var view : MainActivityContract.ViewCreate? = v
    private var api = Api.instance()

    override fun validate(squads_name: String, description: String): Boolean {
        return true
    }

    override fun save(token: String, squads_name: String, description: String) {
        api.createData("Bearer " + token, squads_name, description).enqueue(object : Callback<WrapedResponse<Post>>{
            override fun onFailure(call: Call<WrapedResponse<Post>>, t: Throwable) {
                view?.toast("tidak ada koneksi")
            }

            override fun onResponse(
                call: Call<WrapedResponse<Post>>,
                response: Response<WrapedResponse<Post>>
            ) {
                if (response.isSuccessful){
                    val body = response.body()
                    if (body != null && body.status !== null) {
                        println("body " + body)
                        view?.success("Berhasil "+ body.status)

                    }
                } else {
                    println("body error" + response.body())
                    view?.toast("ada yang tidak benar, "+ response.body())
                }
                view?.isLoading(false)
            }
        })
    }

    override fun destroy() {
        view = null
    }

}
