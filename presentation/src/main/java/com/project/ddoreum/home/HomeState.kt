package com.project.ddoreum.home

sealed interface HomeState {

    object Init : HomeState
    object ClickCert: HomeState

}