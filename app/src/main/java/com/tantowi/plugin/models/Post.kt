package com.tantowi.plugin.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Post(
        @SerializedName("id") var id : String? = null,
        @SerializedName("squads_name") var squads_name : String? = null,
        @SerializedName("description") var description : String? = null
) : Parcelable