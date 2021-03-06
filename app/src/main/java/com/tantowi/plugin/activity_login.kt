package com.tantowi.plugin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.tantowi.plugin.contracts.LoginActivityContract
import com.tantowi.plugin.presenters.LoginactivityPresenter
import com.tantowi.plugin.utilities.Util
import kotlinx.android.synthetic.main.activity_login.*

class activity_login : AppCompatActivity(), LoginActivityContract.View {

    private var presenter = LoginactivityPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        presenter = LoginactivityPresenter(this)

        isLoggedIn()
        Login()
    }

    private fun Login() {
        btnLogin.setOnClickListener {
            val username = etusername.text.toString().trim()
            val password = etPass.text.toString().trim()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                presenter.login(username, password)
            } else {
                toast("isi semua form")
            }
        }
    }

    private fun isLoggedIn() {
        val token = Util.getToken(this)
        if (token != null){
            startActivity(Intent(this, MainActivity::class.java)).also { finish() }
        }
    }

    override fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun success(token: String, id: String) {
        Util.setToken(this,token,id)
        startActivity(Intent(this, MainActivity::class.java)).also { finish() }
    }

    override fun isLoading(state: Boolean) {
        btnLogin.isEnabled = !state
    }

    override fun idError(err: String) {
        inUsername.error = err
    }

    override fun passwordErr(err: String?) {
        inPass.error = err
    }

    override fun notConnect() {
        val token = Util.getToken(this)
        if (token != null) {
            startActivity(Intent(this, MainActivity::class.java)).also { finish() }
        }
    }
}