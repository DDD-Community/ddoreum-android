package com.project.ddoreum.domain.usecase.intro

import com.project.ddoreum.domain.repository.MountainRepository
import com.project.ddoreum.domain.usecase.base.UseCase
import javax.inject.Inject

class SetUserInfoUseCase @Inject constructor(
    private val repository: MountainRepository
) : UseCase<Triple<String, String, String?>, Unit>() {
    override suspend fun execute(param: Triple<String, String, String?>) {
        repository.saveUserInfo(param)
    }
}