package com.tantowi.plugin.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Theory(
        @SerializedName("gathering") var gathering : String? = null,
        @SerializedName("description") var description : String? = null,
        @SerializedName("content") var content : String? = null,
        @SerializedName("date") var date : String? = null,
        @SerializedName("squad_id") var squad_id : Int? = null,
        @SerializedName("tasks") var tasks : List<Task>? = null
) : Parcelable