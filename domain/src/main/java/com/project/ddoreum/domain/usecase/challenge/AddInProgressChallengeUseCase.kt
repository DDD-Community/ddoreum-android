package com.project.ddoreum.domain.usecase.challenge

import com.project.ddoreum.domain.entity.challenge.RequestInProgressChallenge
import com.project.ddoreum.domain.repository.ChallengeRepository
import com.project.ddoreum.domain.usecase.base.UseCase
import javax.inject.Inject

class AddInProgressChallengeUseCase @Inject constructor(
    private val repository: ChallengeRepository
) : UseCase<RequestInProgressChallenge, Unit>() {
    override suspend fun execute(param: RequestInProgressChallenge) {
        repository.setInProgressChallengeData(
            param.challengeId,
            param.challengeType to param.succeedCount
        )
    }
}