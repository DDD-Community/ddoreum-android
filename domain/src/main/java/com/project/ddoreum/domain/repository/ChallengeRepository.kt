package com.project.ddoreum.domain.repository

import com.project.ddoreum.domain.entity.challenge.ChallengeInfoData
import kotlinx.coroutines.flow.Flow

interface ChallengeRepository {
    suspend fun getAllChallengeList(key: String): List<ChallengeInfoData>

    fun getInProgressChallengeData(): Flow<HashMap<Int, Pair<String, Int>>>
    suspend fun setInProgressChallengeData(key: Int, data: Pair<String, Int>)
}