package com.project.ddoreum.domain.entity.challenge

import com.google.gson.annotations.SerializedName

data class ChallengeInfoData(
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
    val verifyList: List<ChallengeMountainData>?,
    @SerializedName("verifyCount")
    val verifyCount: Int?,
    @SerializedName("verifyPeriod")
    val verifyPeriod: String?
) {
    companion object {
        val default = listOf(
            ChallengeInfoData(
                name = "백두대간",
                description = "등산이 처음이라면?\n" +
                        "관악산 연주대",
                type = "",
                verifyList = listOf()
            ),
            ChallengeInfoData(
                name = "감악산",
                description = "여름에 오르기 좋은 \n" +
                        "도봉산 신선대",
                type = "",
                verifyList = listOf()
            ),
            ChallengeInfoData(
                name = "1234산",
                description = "등산이 처음이라면? 관악산 연주대",
                type = "",
                verifyList = listOf()
            )
        )
    }
}