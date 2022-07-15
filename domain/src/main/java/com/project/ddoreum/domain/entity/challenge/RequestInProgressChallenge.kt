package com.project.ddoreum.domain.entity.challenge

data class RequestInProgressChallenge (
    val challengeId: Int,
    val challengeType: String,
    val succeedCount: Int
)