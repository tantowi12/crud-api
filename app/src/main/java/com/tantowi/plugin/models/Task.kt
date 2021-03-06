package com.tantowi.plugin.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Task (
        @SerializedName("title") var title : String? = null,
        @SerializedName("content") var content : String? = null,
        @SerializedName("deadline") var deadline : String? = null
) : Parcelable