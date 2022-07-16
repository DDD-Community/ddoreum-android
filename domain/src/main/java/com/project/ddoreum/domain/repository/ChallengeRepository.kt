package com.project.ddoreum.domain.repository

import com.project.ddoreum.domain.entity.challenge.ChallengeInfoData
import kotlinx.coroutines.flow.Flow
import kotlin.collections.HashMap

interface ChallengeRepository {
    suspend fun getAllChallengeList(key: String): List<ChallengeInfoData>

    fun getInProgressChallengeData(): Flow<HashMap<Int, Triple<String, Int, String>>>
    suspend fun setInProgressChallengeData(key: Int, data: Triple<String, Int, String>)
}