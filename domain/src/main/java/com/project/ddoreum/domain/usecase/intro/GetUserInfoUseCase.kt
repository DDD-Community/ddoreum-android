package com.project.ddoreum.domain.usecase.intro

import com.project.ddoreum.domain.repository.MountainRepository
import com.project.ddoreum.domain.usecase.base.UseCase
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetUserInfoUseCase @Inject constructor(
    private val repository: MountainRepository
) : UseCase<Unit, Flow<Triple<String, String, String?>>>() {
    override suspend fun execute(param: Unit): Flow<Triple<String, String, String?>> {
        return repository.getSavedUserInfo()
    }
}