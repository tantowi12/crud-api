package com.tantowi.plugin.presenters

import com.tantowi.plugin.contracts.LoginActivityContract
import com.tantowi.plugin.models.User
import com.tantowi.plugin.utilities.Util
import com.tantowi.plugin.webservices.Api
import com.tantowi.plugin.webservices.WrapedResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginactivityPresenter (v : LoginActivityContract.View?): LoginActivityContract.Interaction {

    private var view : LoginActivityContract.View? = v
    private var api = Api.instance()

    override fun validate(id: String, password: String): Boolean {
        view?.passwordErr(null)
        if (!Util.isValidPassword(password)) {
            view?.passwordErr("password salah")
            return false
        }
        return false
    }

    override fun login(username: String, password: String) {
        view?.isLoading(true)
        api.login(username, password).enqueue(object : Callback<WrapedResponse<User>>{
            override fun onFailure(call: Call<WrapedResponse<User>>, t: Throwable) {
                view?.toast("cek koneksi anda")
                view?.notConnect()
                view?.isLoading(false)
            }

            override fun onResponse(
                call: Call<WrapedResponse<User>>,
                response: Response<WrapedResponse<User>>
            ) {
                if (response.isSuccessful){
                    val body = response.body()
                    if (body != null && body.status !== null) {
                        view?.toast("selamat datang ${body.data!!.name}")
                        println("body"+body.data)
                        val token = body.data?.token!!
                        val id = body.data?.id!!
                        view?.success(token,id)
                    }
                } else {
                    view?.toast("ada yang salah, cobalagi nanti")
                }
                view?.isLoading(false)
            }
        })
    }

    override fun destroy() {
        view = null
    }
}