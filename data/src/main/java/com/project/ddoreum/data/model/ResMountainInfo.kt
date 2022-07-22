package com.project.ddoreum.data.model

import com.google.gson.annotations.SerializedName

data class ResMountainInfo(
    @SerializedName("info")
    val info: ResMountainDetailInfo,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double
)

