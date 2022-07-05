package com.project.ddoreum.domain.entity.mountain

data class MountainDetailInfoData(
    val name: String?,
    val mountainCode: Int?,
    val mountainDetail: String?,
    val elevation: Int?,
    val hikingNotice: String?,
    val location: String?,
    val mapImage: String?,
    val mountainImage: String?,
    val selectReason: String?,
    val subTitle: String?,
    val transportNotice: String?,
    var isFavorite: Boolean = false
)