package com.project.ddoreum.mountaininfo

import androidx.lifecycle.viewModelScope
import com.project.ddoreum.core.BaseViewModel
import com.project.ddoreum.di.IoDispatcher
import com.project.ddoreum.domain.entity.mountain.MountainInfoData
import com.project.ddoreum.domain.usecase.mountain.GetAllMountainInfoUseCase
import com.project.ddoreum.mountaininfo.state.MainViewType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MountainInfoViewModel @Inject constructor(
    private val getAllMountainInfoUseCase: GetAllMountainInfoUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): BaseViewModel() {

    private val _prevViewType = MutableStateFlow<MainViewType>(MainViewType.MapType)
    private val _mainViewType = MutableStateFlow<MainViewType>(MainViewType.MapType)
    val mainViewType : StateFlow<MainViewType> get() = _mainViewType

    private val _mountainList = MutableStateFlow<ArrayList<MountainInfoData>>(arrayListOf())
    val mountainList :StateFlow<ArrayList<MountainInfoData>> get () = _mountainList

    fun getAllMountainInfo() {
        viewModelScope.launch (ioDispatcher) {
            getAllMountainInfoUseCase.invoke(Unit).onEach { response ->
                response?.let {
                    _mountainList.value = response
                }
            }.launchIn(viewModelScope)
        }
    }

    fun switchMainViewType() = run {
        when (_mainViewType.value) {
            MainViewType.MapType -> _mainViewType.update { MainViewType.ListType }
            MainViewType.ListType -> _mainViewType.update { MainViewType.MapType }
            MainViewType.SearchType -> {
                when (_prevViewType.value) {
                    MainViewType.MapType -> _mainViewType.update { MainViewType.ListType }
                    MainViewType.ListType -> _mainViewType.update { MainViewType.MapType }
                }
            }
        }
    }

    fun switchMainViewTypeToSearch() = run {
        _prevViewType.value = _mainViewType.getAndUpdate {
            MainViewType.SearchType
        }
    }

}