package com.project.ddoreum.data.datasource.mountain

import com.project.ddoreum.data.model.ResMountainDetailInfo
import com.project.ddoreum.data.model.ResMountainInfo
import com.project.ddoreum.domain.ApiResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MountainDataSource {
    suspend fun getAllMountainInfo(): ApiResult<ArrayList<ResMountainInfo>?>
    suspend fun getMountainDetailInfo(name: String): ApiResult<ResMountainDetailInfo?>
    suspend fun getMountainsInfoByKeyword(name: String?, region: String?, regionDetail: String?): ApiResult<ArrayList<ResMountainInfo>?>
}