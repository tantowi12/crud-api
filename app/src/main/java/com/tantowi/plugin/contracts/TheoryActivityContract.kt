package com.tantowi.plugin.contracts

import com.tantowi.plugin.models.Theory

interface TheoryActivityContract {
    interface TheoryActivityView {
        fun attachToRecycle(theory: List<Theory>)
        fun toast(message : String?)
        fun isLoading(state  : Boolean)
        fun notConnect(message: String?)
    }

    interface Interaction {
        fun allTheory(token:String)
        fun destroy()
    }
}