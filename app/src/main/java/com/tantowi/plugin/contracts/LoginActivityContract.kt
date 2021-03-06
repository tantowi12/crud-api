package com.tantowi.plugin.contracts

interface LoginActivityContract {
    interface View{
        fun toast(message : String)
        fun success(token : String, user_id : String)
        fun isLoading(state : Boolean)
        fun idError(err : String)
        fun passwordErr(err: String?)
        fun notConnect()
    }

    interface Interaction{
        fun validate(id : String, password : String) : Boolean
        fun login(username : String, password: String)
        fun destroy()
    }
}