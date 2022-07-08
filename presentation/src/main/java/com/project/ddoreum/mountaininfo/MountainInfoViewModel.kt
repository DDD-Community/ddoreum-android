package com.project.ddoreum.mountaininfo

import androidx.lifecycle.viewModelScope
import com.project.ddoreum.common.SearchKeywordUtil
import com.project.ddoreum.core.BaseViewModel
import com.project.ddoreum.di.IoDispatcher
import com.project.ddoreum.domain.entity.mountain.MountainInfoData
import com.project.ddoreum.domain.usecase.mountain.GetAllMountainInfoUseCase
import com.project.ddoreum.domain.usecase.mountain.GetMountainsInfoByKeywordUseCase
import com.project.ddoreum.mountaininfo.state.MainViewType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MountainInfoViewModel @Inject constructor(
    private val getAllMountainInfoUseCase: GetAllMountainInfoUseCase,
    private val getMountainsInfoByKeywordUseCase: GetMountainsInfoByKeywordUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : BaseViewModel() {

    private val _prevViewType = MutableStateFlow<MainViewType>(MainViewType.MapType)
    private val _mainViewType = MutableStateFlow<MainViewType>(MainViewType.MapType)
    val mainViewType: StateFlow<MainViewType> get() = _mainViewType

    private val _allMountainList = MutableStateFlow<ArrayList<MountainInfoData>>(arrayListOf())
    private val _mountainList = MutableStateFlow<ArrayList<MountainInfoData>>(arrayListOf())
    val mountainList: StateFlow<ArrayList<MountainInfoData>> get() = _mountainList

    private val _searchKeyword = MutableStateFlow<String?>(null)
    private val _searchRegion = MutableStateFlow<Pair<String, String>?>(null)

    fun initAllMountainData() {
        viewModelScope.launch(ioDispatcher) {
            getAllMountainInfoUseCase.invoke(Unit).onEach {
                it?.let {
                    _allMountainList.value = it
                    _mountainList.value = it
                }
            }.launchIn(viewModelScope)
        }
    }

    fun getMountainsInfoByOrder() {
        viewModelScope.launch(ioDispatcher) {
            combine(_searchKeyword, _searchRegion) { keyword, region ->
                if (!keyword.isNullOrBlank()) {
                    flow {
                        emit(search(keyword, _allMountainList.value))
                    }
                } else if (region != null) {
                    getAllMountainInfoUseCase.invoke(Unit)
                } else {
                    emptyFlow()
                }
            }.flatMapLatest {
                it.map {
                    if (it != null) {
                        _mountainList.value = it
                        switchMainViewType(MainViewType.SearchResultType)
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    private fun search(keyword: String, list: ArrayList<MountainInfoData>): ArrayList<MountainInfoData> {
        val result = mutableListOf<MountainInfoData>()
        list.forEach {
            it.mountainInfo.name?.let { name ->
                if (SearchKeywordUtil.match(keyword, name)){
                    result.add(it)
                }
            }
        }
        return result as ArrayList<MountainInfoData>
    }

    fun switchMainViewType() {
        when (_mainViewType.value) {
            MainViewType.MapType -> _mainViewType.update { MainViewType.ListType }
            MainViewType.ListType,
            MainViewType.SearchResultType -> _mainViewType.update { MainViewType.MapType }
            MainViewType.SearchType -> {
                when (_prevViewType.value) {
                    MainViewType.MapType -> _mainViewType.update { MainViewType.ListType }
                    MainViewType.ListType -> _mainViewType.update { MainViewType.MapType }
                    else -> {
                        //ignore
                    }
                }
            }
        }
    }

    private fun switchMainViewType(type: MainViewType) {
        _mainViewType.update { type }
    }

    fun switchMainViewTypeToSearch() = run {
        _prevViewType.value = _mainViewType.getAndUpdate {
            MainViewType.SearchType
        }
    }

    fun updateSearchKeyword(keyword: String) {
        _searchKeyword.value = keyword
    }

    fun updateSearchRegion(region: String, regionDetail: String) {
        _searchRegion.value = region to regionDetail
    }

}
