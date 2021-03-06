package com.tantowi.plugin.contracts

import com.tantowi.plugin.models.Post

interface MainActivityContract {

    interface MainActivityView {
        fun attachToRecycle(squad : List<Post>)
        fun toast(message : String?)
        fun isLoading(state  : Boolean)
        fun notConnect(message: String?)
    }

    interface ViewCreate{
        fun success(message: String?)
        fun toast(message: String?)
        fun isLoading(state: Boolean)
    }

    interface ViewEdit{
        fun success(message: String?)
        fun toast(message: String?)
        fun isLoading(state: Boolean)
    }

    interface ViewDelete{
        fun success(message: String?)
        fun toast(message: String?)
        fun isLoading(state: Boolean)
    }

    interface Interaction{
        fun allUser(token: String)
        fun destroy()
    }

    interface InteractionPost{
        fun validate (squad: String, description: String): Boolean
        fun save(token: String, squad: String, description: String)
        fun destroy()
    }

    interface InteractionUpdate {
        fun validate (squad: String, description: String): Boolean
        fun update(id: Int, token: String, squad: String, description: String)
        fun destroy()
    }

    interface InteractionDelete {
        fun delete(id: Int, token: String)
        fun destroy()
    }
}