package com.project.ddoreum.data.datasource.local

import com.project.ddoreum.domain.entity.mountain.MountainDetailInfoData
import kotlinx.coroutines.flow.Flow
import kotlin.collections.HashMap
import kotlin.collections.HashSet

interface LocalDataSource {
    fun addFavoriteMountain(data: MountainDetailInfoData)
    fun deleteFavoriteMountain(data: MountainDetailInfoData)
    fun getAllFavoriteMountainList(): Flow<HashSet<String>>

    fun addRecentSearchKeyword(keyword: String)
    fun deleteRecentSearchKeyword(keyword: String)
    fun getAllRecentSearchKeyword(): Flow<HashSet<String>>

    fun getUserInfo(): Flow<Triple<String, String, String?>>
    fun setUserInfo(userInfo: Triple<String, String, String?>)

    var recentSearchKeywordList: HashSet<String>
    var favoriteMountainList: HashSet<String>

    var userInfoData: Triple<String, String, String?> // name, email, image
    var inProgressChallengeData: HashMap<Int, Triple<String, Int, String>> //challenge_id, Triple(challenge_type, succeed_count, start_date)
}