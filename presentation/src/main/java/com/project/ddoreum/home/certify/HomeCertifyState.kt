package com.project.ddoreum.home.certify

sealed interface HomeCertifyState {

    object Init : HomeCertifyState
    object CLick: HomeCertifyState

}