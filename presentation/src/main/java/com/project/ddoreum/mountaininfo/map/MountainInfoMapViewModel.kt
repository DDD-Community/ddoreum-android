package com.project.ddoreum.mountaininfo.map

import com.project.ddoreum.core.BaseViewModel
import com.project.ddoreum.domain.entity.mountain.MountainDetailInfoData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MountainInfoMapViewModel @Inject constructor(): BaseViewModel() {

    private val _clickedMountainData = MutableStateFlow<MountainDetailInfoData?>(null)
    val clickedMountainData: StateFlow<MountainDetailInfoData?> get() = _clickedMountainData

    private val _mountainCardViewVisibleState = MutableStateFlow(false)
    val mountainCardViewVisibleState: StateFlow<Boolean> get() = _mountainCardViewVisibleState

    fun updateClickedMountainData(data: MountainDetailInfoData) {
        _clickedMountainData.value = data
    }

    fun updateMountainCardViewState(visible: Boolean) {
        _mountainCardViewVisibleState.update { visible }
    }
}