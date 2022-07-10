package com.project.ddoreum.domain.usecase.mountain

import com.project.ddoreum.domain.entity.mountain.MountainDetailInfoData
import com.project.ddoreum.domain.repository.MountainRepository
import com.project.ddoreum.domain.usecase.base.UseCase
import javax.inject.Inject

class AddFavoriteMountainUseCase @Inject constructor(
    private val repository: MountainRepository
) : UseCase<MountainDetailInfoData, Unit>() {

    override suspend fun execute(param: MountainDetailInfoData) {
        repository.saveFavoriteMountain(param)
    }
}