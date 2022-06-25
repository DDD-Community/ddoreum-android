package com.project.ddoreum.data.model

import com.google.gson.annotations.SerializedName

data class ResMountainDetailInfo(
    @SerializedName("code")
    val code: Int,
    @SerializedName("detail")
    val detail: String,
    @SerializedName("elevation")
    val elevation: Int,
    @SerializedName("hikingScript")
    val hikingScript: String,
    @SerializedName("location")
    val location: String,
    @SerializedName("mapImage")
    val mapImage: String,
    @SerializedName("mountainImage")
    val mountainImage: String,
    @SerializedName("reason")
    val reason: String,
    @SerializedName("subTitle")
    val subTitle: String,
    @SerializedName("transportScript")
    val transportScript: String
)