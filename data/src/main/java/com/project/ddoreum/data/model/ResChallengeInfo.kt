package com.project.ddoreum.data.model

import com.google.gson.annotations.SerializedName

data class ResChallengeInfo(
    @SerializedName("description")
    val description: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("type")
    val type: String,
    @SerializedName("displayImage")
    val displayImage: String,
    @SerializedName("count")
    val count: Int,
    @SerializedName("verifyList")
    val verifyList: List<ResChallengeMountainInfo>?,
    @SerializedName("verifyCount")
    val verifyCount: Int?,
    @SerializedName("verifyPeriod")
    val verifyPeriod: String?
)