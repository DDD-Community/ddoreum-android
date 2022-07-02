package com.project.ddoreum.model

import com.project.ddoreum.R

class PermissionInformation(
    val title: String,
    val icon: Int,
    val isEssential: Boolean,
    val description: String
) {
    companion object {
        val list = listOf(
            PermissionInformation(
                "카메라",
                R.drawable.ic_permission_camera,
                true,
                "실시간 촬영을 통해 산행을 기록하기 위해 필요합니다."
            ),
            PermissionInformation(
                "저장공간",
                R.drawable.ic_permission_storage,
                true,
                "산행 기록을 위해 촬영한 사진이 저장됩니다."
            ),
            PermissionInformation(
                "위치",
                R.drawable.ic_permission_location,
                true,
                "사용자의 행동을 추적하고, 산행 인증을 위한 필수 정보로 활용됩니다."
            ),
            PermissionInformation(
                "전화",
                R.drawable.ic_permission_call,
                false,
                "긴급 전화 등 음성제어를 위해 필요한 권한입니다."
            )
        )
    }
}

