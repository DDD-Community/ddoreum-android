package com.project.ddoreum.domain.usecase.mountain

import com.project.ddoreum.domain.entity.mountain.MountainInfoData
import com.project.ddoreum.domain.repository.MountainRepository
import com.project.ddoreum.domain.usecase.base.FlowUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllMountainInfoUseCase @Inject constructor(
    private val mountainRepository: MountainRepository
) : FlowUseCase<Unit, ArrayList<MountainInfoData>>() {

    override suspend fun execute(param: Unit): Flow<ArrayList<MountainInfoData>> {
        return mountainRepository.getAllMountainInfo()
    }

}