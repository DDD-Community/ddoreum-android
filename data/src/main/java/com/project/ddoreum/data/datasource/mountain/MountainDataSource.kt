package com.project.ddoreum.data.datasource.mountain

import com.project.ddoreum.data.model.ResMountainInfo
import com.project.ddoreum.domain.ApiResult

interface MountainDataSource {
    suspend fun getAllMountainInfo(): ApiResult<ArrayList<ResMountainInfo>?>
}