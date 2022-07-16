package com.project.ddoreum.domain.usecase.challenge

import com.project.ddoreum.domain.repository.ChallengeRepository
import com.project.ddoreum.domain.usecase.base.FlowUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import kotlin.collections.HashMap

class GetAllINProgressChallengeUseCase @Inject constructor(
    private val challengeRepository: ChallengeRepository
) : FlowUseCase<Unit, HashMap<Int, Triple<String, Int, String>>>() {
    override suspend fun execute(param: Unit): Flow<HashMap<Int, Triple<String, Int, String>>> {
        return challengeRepository.getInProgressChallengeData()
    }
}