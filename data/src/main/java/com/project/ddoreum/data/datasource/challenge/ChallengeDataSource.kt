package com.project.ddoreum.data.datasource.challenge

import com.project.ddoreum.data.model.ResChallengeInfo
import kotlin.properties.ReadOnlyProperty

interface ChallengeDataSource {
    suspend fun getAllChallengeList(key: String): ReadOnlyProperty<Any?, List<ResChallengeInfo>>
}