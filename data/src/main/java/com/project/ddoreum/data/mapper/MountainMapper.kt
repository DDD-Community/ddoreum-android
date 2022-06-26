package com.project.ddoreum.data.mapper

import com.project.ddoreum.data.model.ResMountainDetailInfo
import com.project.ddoreum.data.model.ResMountainInfo
import com.project.ddoreum.domain.entity.mountain.MountainDetailInfoData
import com.project.ddoreum.domain.entity.mountain.MountainInfoData

internal fun ResMountainInfo.mapToEntity(): MountainInfoData {
    return MountainInfoData(
        mountainInfo = this.info.mapToEntity(),
        latitude = this.latitude,
        longitude = this.longitude,
        name = this.name
    )
}

internal fun ResMountainDetailInfo.mapToEntity(): MountainDetailInfoData {
    return MountainDetailInfoData(
        mountainCode = this.code,
        mountainDetail = this.detail,
        elevation = this.elevation,
        hikingNotice = this.hikingScript,
        location = this.location,
        mapImage = this.mapImage,
        mountainImage = this.mountainImage,
        selectReason = this.reason,
        subTitle = this.subTitle,
        transportNotice = this.transportScript
    )
}