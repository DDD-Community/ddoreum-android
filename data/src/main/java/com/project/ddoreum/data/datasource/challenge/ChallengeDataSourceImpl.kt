package com.project.ddoreum.data.datasource.challenge

import com.project.ddoreum.data.model.ResChallengeInfo
import javax.inject.Inject
import kotlin.properties.ReadOnlyProperty

class ChallengeDataSourceImpl @Inject constructor(
    private val remoteConfigHelper: RemoteConfigHelper
) : ChallengeDataSource {

    override suspend fun getAllChallengeList(key: String): ReadOnlyProperty<Any?, List<ResChallengeInfo>> {
        remoteConfigHelper.initialize(minimumFetchIntervalInSeconds = 0)
        remoteConfigHelper.fetchAndActivate()
        return RemoteConfigChallengeDelegate(key)
    }

}