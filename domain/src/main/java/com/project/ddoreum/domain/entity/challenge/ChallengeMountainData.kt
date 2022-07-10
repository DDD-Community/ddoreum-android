package com.project.ddoreum.domain.entity.challenge

import com.google.gson.annotations.SerializedName

data class ChallengeMountainData(
    @SerializedName("code")
    val code: Any,
    @SerializedName("elevation")
    val elevation: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String
)