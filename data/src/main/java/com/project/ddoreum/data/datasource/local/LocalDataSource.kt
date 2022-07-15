package com.project.ddoreum.data.datasource.local

import com.project.ddoreum.domain.entity.mountain.MountainDetailInfoData
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    fun addFavoriteMountain(data: MountainDetailInfoData)
    fun deleteFavoriteMountain(data: MountainDetailInfoData)
    fun getAllFavoriteMountainList(): Flow<HashSet<String>>

    fun addRecentSearchKeyword(keyword: String)
    fun deleteRecentSearchKeyword(keyword: String)
    fun getAllRecentSearchKeyword(): Flow<HashSet<String>>

    fun getUserInfo(): Flow<Triple<String, String, String?>>
    fun setUserInfo(userInfo: Triple<String, String, String?>)

    fun getInProgressChallengeData(): Flow<HashMap<Int, Pair<String, Int>>>
    fun setInProgressChallengeData(key: Int, data: Pair<String, Int>)

    var recentSearchKeywordList: HashSet<String>
    var favoriteMountainList: HashSet<String>

    var userInfoData: Triple<String, String, String?> // name, email, image
    var inProgressChallengeData: HashMap<Int, Pair<String, Int>> //challenge_id, Pair(challenge_type, succeed_count)
}