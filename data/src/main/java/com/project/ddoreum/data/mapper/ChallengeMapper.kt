package com.project.ddoreum.data.mapper

import com.project.ddoreum.data.model.ResChallengeInfo
import com.project.ddoreum.data.model.ResChallengeMountainInfo
import com.project.ddoreum.domain.entity.challenge.ChallengeInfoData
import com.project.ddoreum.domain.entity.challenge.ChallengeMountainData

internal fun ResChallengeInfo.mapToEntity(): ChallengeInfoData {
    return ChallengeInfoData(
        description = description,
        name = name,
        id = id,
        type = type,
        displayImage = displayImage,
        count = count,
        verifyList = verifyList?.map {
            it.mapToEntity()
        },
        verifyPeriod = verifyPeriod,
        verifyCount = verifyCount
    )
}

internal fun ResChallengeMountainInfo.mapToEntity(): ChallengeMountainData {
    return ChallengeMountainData(
        code = code,
        elevation = elevation,
        image = image,
        name = name
    )
}