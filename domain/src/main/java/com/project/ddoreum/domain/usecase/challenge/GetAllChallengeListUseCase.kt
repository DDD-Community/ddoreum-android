package com.project.ddoreum.domain.usecase.challenge

import com.project.ddoreum.domain.entity.challenge.ChallengeInfoData
import com.project.ddoreum.domain.repository.ChallengeRepository
import com.project.ddoreum.domain.usecase.base.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllChallengeListUseCase @Inject constructor(
    private val challengeRepository: ChallengeRepository
) : FlowUseCase<String, List<ChallengeInfoData>>() {

    override suspend fun execute(key: String): Flow<List<ChallengeInfoData>> {
        val result = challengeRepository.getAllChallengeList(key)
        return flow {
            emit(result)
        }
    }
}