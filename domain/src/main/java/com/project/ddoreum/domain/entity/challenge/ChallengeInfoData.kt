package com.project.ddoreum.domain.entity.challenge

import com.google.gson.annotations.SerializedName

data class ChallengeInfoData(
    @SerializedName("description")
    val description: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("verifyList")
    val verifyList: List<ChallengeMountainData>
)