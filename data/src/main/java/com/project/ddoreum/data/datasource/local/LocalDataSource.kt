package com.project.ddoreum.data.datasource.local

import com.project.ddoreum.domain.entity.mountain.MountainDetailInfoData
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    fun addFavoriteMountain(data: MountainDetailInfoData)
    fun deleteFavoriteMountain(data: MountainDetailInfoData)
    fun getAllFavoriteMountainList(): Flow<HashSet<String>>

    var favoriteMountainList: HashSet<String>
}