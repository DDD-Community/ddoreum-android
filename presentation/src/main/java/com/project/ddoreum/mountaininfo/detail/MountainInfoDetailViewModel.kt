package com.project.ddoreum.mountaininfo.detail

import androidx.lifecycle.viewModelScope
import com.project.ddoreum.core.BaseViewModel
import com.project.ddoreum.di.IoDispatcher
import com.project.ddoreum.domain.entity.mountain.MountainDetailInfoData
import com.project.ddoreum.domain.usecase.mountain.GetMountainDetailInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MountainInfoDetailViewModel @Inject constructor(
    private val getMountainDetailInfoUseCase: GetMountainDetailInfoUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): BaseViewModel() {

    private val _mountainDetailData = MutableStateFlow<MountainDetailInfoData?>(null)
    val mountainDetailData: StateFlow<MountainDetailInfoData?> get() = _mountainDetailData

    fun getMountainDetailInfo(name: String) {
        viewModelScope.launch(ioDispatcher) {
            getMountainDetailInfoUseCase.invoke(name).onEach {
                _mountainDetailData.value = it
            }.launchIn(viewModelScope)
        }
    }
}