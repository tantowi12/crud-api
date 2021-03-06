package com.tantowi.plugin.presenters

import com.tantowi.plugin.contracts.MainActivityContract
import com.tantowi.plugin.models.Post
import com.tantowi.plugin.webservices.Api
import com.tantowi.plugin.webservices.WrapedResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DeleteActivityPresenter (v: MainActivityContract.ViewDelete) : MainActivityContract.InteractionDelete {

    private var view : MainActivityContract.ViewDelete? = v
    private var api = Api.instance()

    override fun delete(id: Int, token: String) {
        val request = api.deleteData(id, "Bearer " +token)
        request.enqueue(object : Callback<WrapedResponse<Post>>{
            override fun onFailure(call: Call<WrapedResponse<Post>>, t: Throwable) {
                view?.toast("tidak ada koneksi, coba lagi nanti")
            }

            override fun onResponse(
                call: Call<WrapedResponse<Post>>,
                response: Response<WrapedResponse<Post>>
            ) {
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null && body.status !== null) {
                        view?.success("Berhasil")
                    } else {
                        view?.toast("ada yang tidak beres, " + response.body())
                    }
                    view?.isLoading(false)
                }
            }

        })
    }

    override fun destroy() {
        view = null
    }

}