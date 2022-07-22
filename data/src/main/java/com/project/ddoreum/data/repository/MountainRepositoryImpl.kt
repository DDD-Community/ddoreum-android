package com.project.ddoreum.data.repository

import com.project.ddoreum.data.datasource.local.LocalDataSource
import com.project.ddoreum.data.datasource.mountain.MountainDataSource
import com.project.ddoreum.data.mapper.mapToEntity
import com.project.ddoreum.domain.ApiResult
import com.project.ddoreum.domain.entity.mountain.MountainDetailInfoData
import com.project.ddoreum.domain.entity.mountain.MountainInfoData
import com.project.ddoreum.domain.repository.MountainRepository
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class MountainRepositoryImpl @Inject constructor(
    private val mountainDataSource: MountainDataSource,
    private val localDataSource: LocalDataSource
) : MountainRepository {

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

    override suspend fun getMountainsInfoByKeyword(
        region: String?,
        regionDetail: String?
    ): Flow<ArrayList<MountainInfoData>?> {
        val result = mountainDataSource.getMountainsInfoByKeyword(region, regionDetail)
        return when (result.status) {
            ApiResult.Status.SUCCESS -> {
                flow {
                    emit(
                        result.responseData?.map { resMountainInfo ->
                            resMountainInfo.mapToEntity()
                        }?.toCollection(arrayListOf())
                    )
                }
            }
            ApiResult.Status.ERROR -> {
                emptyFlow()
            }
        }
    }

    override fun getAllFavoriteMountain(): Flow<HashSet<String>> {
        return localDataSource.getAllFavoriteMountainList()
    }

    override suspend fun saveFavoriteMountain(data: MountainDetailInfoData) {
        withContext(Dispatchers.IO) {
            localDataSource.addFavoriteMountain(data)
        }
    }

    override suspend fun deleteFavoriteMountain(data: MountainDetailInfoData) {
        withContext(Dispatchers.IO) {
            localDataSource.deleteFavoriteMountain(data)
        }
    }

    override fun getAllRecentSearchKeyword(): Flow<HashSet<String>> {
        return localDataSource.getAllRecentSearchKeyword()
    }

    override suspend fun saveRecentSearchKeyword(keyword: String) {
        withContext(Dispatchers.IO) {
            localDataSource.addRecentSearchKeyword(keyword)
        }
    }

    override suspend fun deleteRecentSearchKeyword(keyword: String) {
        withContext(Dispatchers.IO) {
            localDataSource.deleteRecentSearchKeyword(keyword)
        }
    }

    override fun getSavedUserInfo(): Flow<Triple<String, String, String?>> {
        return localDataSource.getUserInfo()
    }

    override suspend fun saveUserInfo(userInfo: Triple<String, String, String?>) {
        withContext(Dispatchers.IO) {
            localDataSource.setUserInfo(userInfo)
        }
    }
}