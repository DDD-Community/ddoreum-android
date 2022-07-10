package com.project.ddoreum.data.model

import com.google.gson.annotations.SerializedName

data class ResChallengeMountainInfo(
    @SerializedName("code")
    val code: Any,
    @SerializedName("elevation")
    val elevation: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String
)