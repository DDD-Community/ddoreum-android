package com.project.ddoreum.domain.usecase.mountain

import com.project.ddoreum.domain.entity.mountain.MountainDetailInfoData
import com.project.ddoreum.domain.repository.MountainRepository
import com.project.ddoreum.domain.usecase.base.FlowUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMountainDetailInfoUseCase @Inject constructor(
    private val mountainRepository: MountainRepository
) : FlowUseCase<String, MountainDetailInfoData?>() {

    override suspend fun execute(param: String): Flow<MountainDetailInfoData?> {
        return mountainRepository.getMountainDetailInfo(param)
    }

}