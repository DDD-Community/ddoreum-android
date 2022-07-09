package com.project.ddoreum.data.datasource.mountain

import com.project.ddoreum.data.model.ResMountainDetailInfo
import com.project.ddoreum.data.model.ResMountainInfo
import com.project.ddoreum.domain.ApiResult

interface MountainDataSource {
    suspend fun getAllMountainInfo(): ApiResult<ArrayList<ResMountainInfo>?>
    suspend fun getMountainDetailInfo(name: String): ApiResult<ResMountainDetailInfo?>
    suspend fun getMountainsInfoByKeyword(region: String?, regionDetail: String?): ApiResult<ArrayList<ResMountainInfo>?>
}