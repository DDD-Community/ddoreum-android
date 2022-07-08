package com.project.ddoreum.domain.usecase.mountain

import com.project.ddoreum.domain.entity.mountain.MountainInfoData
import com.project.ddoreum.domain.entity.request.SearchRequestParams
import com.project.ddoreum.domain.repository.MountainRepository
import com.project.ddoreum.domain.usecase.base.FlowUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMountainsInfoByKeywordUseCase @Inject constructor(
    private val mountainRepository: MountainRepository
) : FlowUseCase<SearchRequestParams, ArrayList<MountainInfoData>?>() {

    override suspend fun execute(param: SearchRequestParams): Flow<ArrayList<MountainInfoData>?> {
        return mountainRepository.getMountainsInfoByKeyword(
            name = param.name,
            region = param.region,
            regionDetail = param.regionDetail
        )
    }

}