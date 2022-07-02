package com.project.ddoreum.mountaininfo

import androidx.lifecycle.viewModelScope
import com.project.ddoreum.core.BaseViewModel
import com.project.ddoreum.di.IoDispatcher
import com.project.ddoreum.domain.usecase.mountain.GetAllMountainInfoUseCase
import com.project.ddoreum.mountaininfo.state.MainViewType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MountainInfoViewModel @Inject constructor(
    private val getAllMountainInfoUseCase: GetAllMountainInfoUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): BaseViewModel() {

    private val _mainViewType = MutableStateFlow<MainViewType>(MainViewType.MapType)
    val mainViewType : StateFlow<MainViewType> get() = _mainViewType

    fun getAllMountainInfo() {
        viewModelScope.launch (ioDispatcher) {
            getAllMountainInfoUseCase.invoke(Unit)
        }
    }

    fun switchMainViewType() = run {
        when (_mainViewType.value) {
            MainViewType.MapType -> {
                _mainViewType.update { MainViewType.ListType }
            }
            MainViewType.ListType -> {
                _mainViewType.update { MainViewType.MapType }
            }
        }
    }

    fun switchMainViewTypeToSearch() = run {
        _mainViewType.update { MainViewType.SearchType }
    }

}