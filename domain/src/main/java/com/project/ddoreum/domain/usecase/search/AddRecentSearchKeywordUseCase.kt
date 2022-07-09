package com.project.ddoreum.domain.usecase.search

import com.project.ddoreum.domain.repository.MountainRepository
import com.project.ddoreum.domain.usecase.base.UseCase
import javax.inject.Inject

class AddRecentSearchKeywordUseCase @Inject constructor(
    private val repository: MountainRepository
) : UseCase<String, Unit>() {

    override suspend fun execute(param: String) {
        repository.saveRecentSearchKeyword(param)
    }
}