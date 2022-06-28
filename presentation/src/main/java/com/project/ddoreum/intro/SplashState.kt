package com.project.ddoreum.intro

sealed interface SplashState {

    object Init : SplashState
    object Permission : SplashState
    object RejectPermission : SplashState
    object Login : SplashState
    object Finish : SplashState

}