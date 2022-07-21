package com.project.ddoreum.home

sealed interface HomeViewEvent {

    object ClickChallenge : HomeViewEvent
    object ClickCert : HomeViewEvent

}