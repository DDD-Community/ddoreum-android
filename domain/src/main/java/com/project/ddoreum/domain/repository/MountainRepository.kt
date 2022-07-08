package com.project.ddoreum.domain.repository

import com.project.ddoreum.domain.entity.mountain.MountainDetailInfoData
import com.project.ddoreum.domain.entity.mountain.MountainInfoData
import kotlinx.coroutines.flow.Flow

interface MountainRepository {
    suspend fun getAllMountainInfo(): Flow<ArrayList<MountainInfoData>?>
    suspend fun getMountainDetailInfo(name: String): Flow<MountainDetailInfoData?>
    suspend fun getMountainsInfoByKeyword(name: String?, region: String?, regionDetail: String?): Flow<ArrayList<MountainInfoData>?>

    fun getAllFavoriteMountain(): Flow<HashSet<String>>
    suspend fun saveFavoriteMountain(data: MountainDetailInfoData)
    suspend fun deleteFavoriteMountain(data: MountainDetailInfoData)
}