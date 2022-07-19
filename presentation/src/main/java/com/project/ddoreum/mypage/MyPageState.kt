package com.project.ddoreum.mypage

sealed interface MyPageState {

    object Init : MyPageState
    object OpenSource: MyPageState

}