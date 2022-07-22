package com.project.ddoreum.domain.entity.challenge


data class InProgressChallengeData(
    val name: String,
    val id: Int,
    val type: String,
    val count: Int,
    val verifyList: List<ChallengeMountainData>?,
    val verifyCount: Int?,
    val verifyPeriod: String?,
    val succeedCount: Int = 0,
    val startDate: String,
    val endDate: String
)