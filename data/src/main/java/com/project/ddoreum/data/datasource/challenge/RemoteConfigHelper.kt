package com.project.ddoreum.data.datasource.challenge

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class RemoteConfigHelper {

    suspend fun initialize(
        minimumFetchIntervalInSeconds: Long = 60 * 15,
        fetchTimeoutInSeconds: Long = 10L
    ) = suspendCoroutine<Unit> { continuation ->
            FirebaseRemoteConfig.getInstance().apply {
                val config = FirebaseRemoteConfigSettings.Builder()
                    .setMinimumFetchIntervalInSeconds(minimumFetchIntervalInSeconds)
                    .setFetchTimeoutInSeconds(fetchTimeoutInSeconds)
                    .build()
                setConfigSettingsAsync(config)
                    .addOnSuccessListener {
                        continuation.resume(Unit)
                    }
                    .addOnFailureListener {
                        continuation.resumeWithException(it)
                    }
            }
    }

    suspend fun fetchAndActivate() = suspendCoroutine<Boolean> { continuation ->  
        FirebaseRemoteConfig.getInstance().fetchAndActivate()
            .addOnSuccessListener {
                continuation.resume(it)
            }
            .addOnFailureListener {
                continuation.resumeWithException(it)
            }
    }
}