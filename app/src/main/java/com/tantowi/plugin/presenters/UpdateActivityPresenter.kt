package com.tantowi.plugin.presenters

import com.tantowi.plugin.contracts.MainActivityContract
import com.tantowi.plugin.models.Post
import com.tantowi.plugin.webservices.Api
import com.tantowi.plugin.webservices.WrapedResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateActivityPresenter (v: MainActivityContract.ViewEdit) : MainActivityContract.InteractionUpdate{

    private var view : MainActivityContract.ViewEdit? = v
    private var api = Api.instance()

    override fun validate(squad: String, description: String): Boolean {
        return true
    }

    override fun update(id: Int, token: String, squads_name: String, description: String) {
        val requestUpdate = api.updateData(id,"Bearer " + token ,  squads_name, description)
        requestUpdate.enqueue(object : Callback<WrapedResponse<Post>>{
            override fun onFailure(call: Call<WrapedResponse<Post>>, t: Throwable) {
                view?.toast("konekti tidak bisa, coba lagi nanti")
            }

            override fun onResponse(
                    call: Call<WrapedResponse<Post>>,
                    response: Response<WrapedResponse<Post>>
            ) {
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null && body.status !== null) {
                        println("body" + body)
                        view?.success("berhasil " + body.status)
                    }
                } else {
                    view?.toast("ada yang tidak beres caoba lagi nenti" + response.body())
                }
                view?.isLoading(false)
            }
        })
    }

    override fun destroy() {
        view = null
    }


}