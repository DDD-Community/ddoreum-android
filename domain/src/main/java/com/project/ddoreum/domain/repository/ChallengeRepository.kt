package com.project.ddoreum.domain.repository

import com.project.ddoreum.domain.entity.challenge.ChallengeInfoData

interface ChallengeRepository {
    suspend fun getAllChallengeList(key: String): List<ChallengeInfoData>
}