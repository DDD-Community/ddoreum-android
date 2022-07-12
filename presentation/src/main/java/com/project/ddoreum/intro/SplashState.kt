package com.project.ddoreum.intro

sealed interface SplashState {

    object Init : SplashState
    object Permission : SplashState
    object RejectPermission : SplashState
    object SuccessPermission : SplashState
    object Login : SplashState
    object FailLogin : SplashState
    data class Finish(val name: String) : SplashState

}