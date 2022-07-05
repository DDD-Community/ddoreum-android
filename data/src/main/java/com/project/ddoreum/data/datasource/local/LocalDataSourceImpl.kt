package com.project.ddoreum.data.datasource.local

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.project.ddoreum.data.fromJson
import com.project.ddoreum.domain.entity.mountain.MountainDetailInfoData
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import java.lang.Exception
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(@ApplicationContext context: Context): LocalDataSource {

    private val sharedPrefs: SharedPreferences by lazy {
        context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
    }

    private val favoriteMountainFlow = MutableStateFlow(favoriteMountainList)

    override fun addFavoriteMountain(data: MountainDetailInfoData) {
        if (favoriteMountainList == null) {
            favoriteMountainList = hashSetOf()
        }
        favoriteMountainList = favoriteMountainList.apply {
            this.add(data.mountainCode.toString())
        }
    }

    override fun deleteFavoriteMountain(data: MountainDetailInfoData) {
        favoriteMountainList = favoriteMountainList.apply {
            this.remove(data.mountainCode.toString())
        }
    }

    override fun getAllFavoriteMountainList(): Flow<HashSet<String>> {
        return favoriteMountainFlow
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

    companion object {
        const val FAVORITE_LIST = "favorite_list"
    }
}