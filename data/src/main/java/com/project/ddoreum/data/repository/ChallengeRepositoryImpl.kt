package com.project.ddoreum.data.repository

import com.project.ddoreum.data.datasource.challenge.ChallengeDataSource
import com.project.ddoreum.data.datasource.local.LocalDataSource
import com.project.ddoreum.data.mapper.mapToEntity
import com.project.ddoreum.domain.entity.challenge.ChallengeInfoData
import com.project.ddoreum.domain.repository.ChallengeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.collections.HashMap

class ChallengeRepositoryImpl @Inject constructor(
    private val challengeDataSource: ChallengeDataSource,
    private val localDataSource: LocalDataSource
): ChallengeRepository {

    override suspend fun getAllChallengeList(key: String): List<ChallengeInfoData> {
        val list by challengeDataSource.getAllChallengeList(key)
        return if (!list.isNullOrEmpty()) {
            list.map {
                it.mapToEntity()
            }
        } else {
            emptyList()
        }
    }

    override fun getInProgressChallengeData(): Flow<HashMap<Int, Triple<String, Int, String>>> {
        return localDataSource.getInProgressChallengeData()
    }

    override suspend fun setInProgressChallengeData(key: Int, data: Triple<String, Int, String>) {
        withContext(Dispatchers.IO) {
            localDataSource.setInProgressChallengeData(key, data)
        }
    }
}