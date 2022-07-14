package com.project.ddoreum.model

data class UserInfo(
    val name: String,
    val hikingLv: Int,
    val hikingElevation: Int,
    val remainElevation: Int
) {
    companion object {
        val default = UserInfo(
            name = "테스트",
            hikingLv = 3,
            hikingElevation = 150,
            remainElevation = 3750
        )
    }
}