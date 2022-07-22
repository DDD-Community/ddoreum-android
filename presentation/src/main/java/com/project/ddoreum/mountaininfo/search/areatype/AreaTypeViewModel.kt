package com.project.ddoreum.mountaininfo.search.areatype

import com.project.ddoreum.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AreaTypeViewModel @Inject constructor(): BaseViewModel(){
    private val _searchRegion = MutableStateFlow<String?>(null)
    val searchRegion: StateFlow<String?> get() = _searchRegion
    private val _searchRegionDetail = MutableStateFlow<String?>(null)
    val searchRegionDetail: StateFlow<String?> get() = _searchRegionDetail

    fun updateSearchRegion(region: String) {
        _searchRegion.value = region
    }

    fun updateSearchRegionDetail(regionDetail: String) {
        _searchRegionDetail.value = regionDetail
    }

}