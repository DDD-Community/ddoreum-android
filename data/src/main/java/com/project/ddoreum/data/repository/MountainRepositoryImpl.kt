package com.project.ddoreum.data.repository

import com.project.ddoreum.data.datasource.mountain.MountainDataSource
import com.project.ddoreum.data.mapper.mapToEntity
import com.project.ddoreum.domain.ApiResult
import com.project.ddoreum.domain.entity.mountain.MountainDetailInfoData
import com.project.ddoreum.domain.entity.mountain.MountainInfoData
import com.project.ddoreum.domain.repository.MountainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MountainRepositoryImpl @Inject constructor(
    private val mountainDataSource: MountainDataSource
): MountainRepository {

    override suspend fun getAllMountainInfo(): Flow<ArrayList<MountainInfoData>?> {
        val result = mountainDataSource.getAllMountainInfo()
        return if (result.status == ApiResult.Status.SUCCESS) {
            flow {
                emit(
                    result.responseData?.map { resMountainInfo ->
                        resMountainInfo.mapToEntity()
                    }?.toCollection(arrayListOf())
                )
            }
        } else {
            emptyFlow()
        }
    }

    override suspend fun getMountainDetailInfo(name: String): Flow<MountainDetailInfoData?> {
        val result = mountainDataSource.getMountainDetailInfo(name)
        return if (result.status == ApiResult.Status.SUCCESS) {
            flow {
                emit(result.responseData?.mapToEntity())
            }
        } else {
            emptyFlow()
        }
    }

}