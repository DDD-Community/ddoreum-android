package com.project.ddoreum.domain.usecase.challenge

import com.project.ddoreum.domain.repository.ChallengeRepository
import com.project.ddoreum.domain.usecase.base.FlowUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllINProgressChallengeUseCase @Inject constructor(
    private val challengeRepository: ChallengeRepository
) : FlowUseCase<Unit, HashMap<Int, Pair<String, Int>>>() {
    override suspend fun execute(param: Unit): Flow<HashMap<Int, Pair<String, Int>>> {
        return challengeRepository.getInProgressChallengeData()
    }
}