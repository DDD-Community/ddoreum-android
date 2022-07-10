package com.project.ddoreum.data.model

import com.google.gson.annotations.SerializedName

data class ResChallengeInfo(
    @SerializedName("description")
    val description: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("verifyList")
    val verifyList: List<ResChallengeMountainInfo>
)