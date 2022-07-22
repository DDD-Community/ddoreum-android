package com.project.ddoreum.domain.usecase.search

import com.project.ddoreum.domain.repository.MountainRepository
import com.project.ddoreum.domain.usecase.base.UseCase
import javax.inject.Inject

class DeleteRecentSearchKeywordUseCase @Inject constructor(
    private val repository: MountainRepository
) : UseCase<String, Unit>() {

    override suspend fun execute(data: String) {
        repository.deleteRecentSearchKeyword(data)
    }
}