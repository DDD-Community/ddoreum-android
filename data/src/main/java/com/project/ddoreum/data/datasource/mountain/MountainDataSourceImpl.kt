package com.project.ddoreum.data.datasource.mountain

import com.project.ddoreum.data.getResult
import com.project.ddoreum.data.model.ResMountainDetailInfo
import com.project.ddoreum.data.model.ResMountainInfo
import com.project.ddoreum.data.service.MountainService
import com.project.ddoreum.domain.ApiResult
import javax.inject.Inject

class MountainDataSourceImpl @Inject constructor(
    private val service: MountainService
) : MountainDataSource {

    override suspend fun getAllMountainInfo(): ApiResult<ArrayList<ResMountainInfo>?> = getResult {
        service.getAllMountainsInfo()
    }

    override suspend fun getMountainDetailInfo(name: String): ApiResult<ResMountainDetailInfo?> = getResult (true) {
        service.getMountainDetailInfo(name)
    }

    override suspend fun getMountainsInfoByKeyword(
        region: String?,
        regionDetail: String?
    ): ApiResult<ArrayList<ResMountainInfo>?> = getResult {
        service.getMountainsInfoByKeyword(region, regionDetail)
    }

}