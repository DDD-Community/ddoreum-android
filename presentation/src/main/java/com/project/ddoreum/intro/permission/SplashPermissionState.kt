package com.project.ddoreum.intro.permission

sealed interface SplashPermissionState {
    object Init : SplashPermissionState
    object Permission : SplashPermissionState
    object RejectPermission : SplashPermissionState
    object Finish : SplashPermissionState
}