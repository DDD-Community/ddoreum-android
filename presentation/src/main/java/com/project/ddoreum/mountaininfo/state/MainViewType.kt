package com.project.ddoreum.mountaininfo.state

sealed interface MainViewType {
    object MapType: MainViewType
    object ListType: MainViewType
    object SearchType: MainViewType
}