package com.project.ddoreum.data.datasource.local

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.project.ddoreum.data.fromJson
import com.project.ddoreum.domain.entity.mountain.MountainDetailInfoData
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class LocalDataSourceImpl @Inject constructor(@ApplicationContext context: Context) :
    LocalDataSource {

    private val sharedPrefs: SharedPreferences by lazy {
        context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
    }

    private val favoriteMountainFlow = MutableStateFlow(favoriteMountainList)

    private val recentSearchKeywordFlow = MutableStateFlow(recentSearchKeywordList)

    private val userInfoFlow = MutableStateFlow(userInfoData)

    override fun addFavoriteMountain(data: MountainDetailInfoData) {
        favoriteMountainList = favoriteMountainList.apply {
            add(data.mountainCode.toString())
        }
    }

    override fun deleteFavoriteMountain(data: MountainDetailInfoData) {
        favoriteMountainList = favoriteMountainList.apply {
            remove(data.mountainCode.toString())
        }
    }

    override fun getAllFavoriteMountainList(): Flow<HashSet<String>> {
        return favoriteMountainFlow
    }

    override fun addRecentSearchKeyword(keyword: String) {
        if (recentSearchKeywordList == null) {
            recentSearchKeywordList = hashSetOf()
        }
        recentSearchKeywordList = recentSearchKeywordList.apply {
            add(keyword)
        }
    }

    override fun deleteRecentSearchKeyword(keyword: String) {
        recentSearchKeywordList = recentSearchKeywordList.apply {
            remove(keyword)
        }
    }

    override fun getAllRecentSearchKeyword(): Flow<HashSet<String>> {
        return recentSearchKeywordFlow
    }

    override fun getUserInfo(): Flow<Triple<String, String, String?>> {
        return userInfoFlow
    }

    override fun setUserInfo(userInfo: Triple<String, String, String?>) {
        userInfoData = userInfo
    }

    override var favoriteMountainList: HashSet<String>
        get() {
            val jsonString = sharedPrefs.getString(FAVORITE_LIST, "") ?: ""
            return try {
                Gson().fromJson(jsonString) as HashSet<String>
            } catch (e: Exception) {
                HashSet()
            }
        }
        set(value) {
            favoriteMountainFlow.value = value
            val jsonString = Gson().toJson(value)
            sharedPrefs.edit().putString(FAVORITE_LIST, jsonString).apply()
        }

    override var recentSearchKeywordList: HashSet<String>
        get() {
            val jsonString = sharedPrefs.getString(RECENT_SEARCH_KEYWORD_LIST, "") ?: ""
            return try {
                Gson().fromJson(jsonString) as HashSet<String>
            } catch (e: Exception) {
                HashSet()
            }
        }
        set(value) {
            recentSearchKeywordFlow.value = value
            val jsonString = Gson().toJson(value)
            sharedPrefs.edit().putString(RECENT_SEARCH_KEYWORD_LIST, jsonString).apply()
        }

    // user info ; name, email, imageUrl
    override var userInfoData: Triple<String, String, String?>
        get() {
            val jsonString = sharedPrefs.getString(LOGGED_IN_USER_INFO, "") ?: ""
            return try {
                return Gson().fromJson(
                    jsonString,
                    object : TypeToken<Triple<String, String, String?>>() {}.type
                ) as? Triple<String, String, String?> ?: Triple("", "", null)
            } catch (e: Exception) {
                Triple("", "", null)
            }
        }
        set(value) {
            userInfoFlow.value = value
            val jsonString = Gson().toJson(value)
            sharedPrefs.edit()
                .putString(LOGGED_IN_USER_INFO, jsonString)
                .apply()
        }

    companion object {
        const val FAVORITE_LIST = "favorite_list"
        const val RECENT_SEARCH_KEYWORD_LIST = "recent_search_keyword_list"
        const val LOGGED_IN_USER_INFO = "logged_in_user_info"
    }
}