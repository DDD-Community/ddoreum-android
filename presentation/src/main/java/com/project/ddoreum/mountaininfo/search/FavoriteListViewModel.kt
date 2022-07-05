package com.project.ddoreum.mountaininfo.search

import androidx.lifecycle.viewModelScope
import com.project.ddoreum.core.BaseViewModel
import com.project.ddoreum.di.IoDispatcher
import com.project.ddoreum.domain.entity.mountain.MountainDetailInfoData
import com.project.ddoreum.domain.usecase.mountain.GetAllFavoriteMountainUseCase
import com.project.ddoreum.domain.usecase.mountain.GetAllMountainInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteListViewModel @Inject constructor(
    private val getAllFavoriteMountainUseCase: GetAllFavoriteMountainUseCase,
    private val getAllMountainInfoUseCase: GetAllMountainInfoUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : BaseViewModel() {

    private val _favoriteList = MutableStateFlow<List<MountainDetailInfoData?>?>(null)
    val favoriteList: StateFlow<List<MountainDetailInfoData?>?> get() = _favoriteList

    fun getAllFavoriteMountainList() {
        viewModelScope.launch(ioDispatcher) {
            val favoriteFlow = getAllFavoriteMountainUseCase.invoke(Unit)
            val mountainFlow = getAllMountainInfoUseCase.invoke(Unit)
            combine(mountainFlow, favoriteFlow) { mountainList, favoriteList ->
                if (favoriteList != null) {
                    _favoriteList.value = mountainList?.filter {
                        val code = checkNotNull(it.mountainInfo.mountainCode.toString())
                        favoriteList.contains(code)
                    }?.map {
                        it.mountainInfo
                    } ?: emptyList()
                } else {
                    _favoriteList.value = emptyList()
                }
            }.launchIn(viewModelScope)
        }
    }

}