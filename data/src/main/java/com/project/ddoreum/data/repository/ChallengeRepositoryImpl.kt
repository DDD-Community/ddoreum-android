package com.project.ddoreum.data.repository

import com.project.ddoreum.data.datasource.challenge.ChallengeDataSource
import com.project.ddoreum.data.mapper.mapToEntity
import com.project.ddoreum.domain.entity.challenge.ChallengeInfoData
import com.project.ddoreum.domain.repository.ChallengeRepository
import javax.inject.Inject

class ChallengeRepositoryImpl @Inject constructor(
    private val challengeDataSource: ChallengeDataSource
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
}