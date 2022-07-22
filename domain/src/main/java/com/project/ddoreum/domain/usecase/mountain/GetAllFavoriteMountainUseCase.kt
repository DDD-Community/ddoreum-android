package com.project.ddoreum.domain.usecase.mountain

import com.project.ddoreum.domain.repository.MountainRepository
import com.project.ddoreum.domain.usecase.base.FlowUseCase
import com.project.ddoreum.domain.usecase.base.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetAllFavoriteMountainUseCase @Inject constructor(
    private val repository: MountainRepository
) : UseCase<Unit, Flow<HashSet<String>>>() {
    override suspend fun execute(param: Unit): Flow<HashSet<String>> {
        return repository.getAllFavoriteMountain()
    }
}