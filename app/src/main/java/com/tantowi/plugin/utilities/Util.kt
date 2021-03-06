package com.tantowi.plugin.utilities

import android.content.Context
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

class Util {
    companion object {

        var API_ENDPOINT = "https://app-server-ta.herokuapp.com/"

        fun getToken(context: Context) : String? {
            val token = context.getSharedPreferences("USER", Context.MODE_PRIVATE)
            return token?.getString("TOKEN", null)
        }

        fun setToken(context: Context, token: String, id: String) {
            val pref = context.getSharedPreferences("USER", Context.MODE_PRIVATE)
            pref.edit().apply() {
                putString("TOKEN", token)
                putString("USER_ID", id)
                apply()
            }
        }

        fun clearToken(context: Context){
            val pref = context.getSharedPreferences("USER", Context.MODE_PRIVATE)
            pref.edit().clear().apply()
        }

        fun isValidPassword(password: String) = password !=  null
    }
}
