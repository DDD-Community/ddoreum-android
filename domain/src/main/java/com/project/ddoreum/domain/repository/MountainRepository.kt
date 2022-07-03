package com.project.ddoreum.domain.repository

import com.project.ddoreum.domain.entity.mountain.MountainDetailInfoData
import com.project.ddoreum.domain.entity.mountain.MountainInfoData
import kotlinx.coroutines.flow.Flow

interface MountainRepository {
    suspend fun getAllMountainInfo(): Flow<ArrayList<MountainInfoData>?>
    suspend fun getMountainDetailInfo(name: String): Flow<MountainDetailInfoData?>
}