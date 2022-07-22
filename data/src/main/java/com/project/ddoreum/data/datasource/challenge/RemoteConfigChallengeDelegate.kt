package com.project.ddoreum.data.datasource.challenge

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.project.ddoreum.data.model.ResChallengeInfo
import java.lang.Exception
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class RemoteConfigChallengeDelegate internal constructor(private val key: String): ReadOnlyProperty<Any?, List<ResChallengeInfo>>{

    override fun getValue(thisRef: Any?, property: KProperty<*>): List<ResChallengeInfo> {
        return try {
            val events = FirebaseRemoteConfig.getInstance().getString(key)
            return Gson().fromJson(events, object : TypeToken<List<ResChallengeInfo>>() {}.type)
        } catch (e: Exception) {
            emptyList()
        }
    }
}

