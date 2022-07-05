package com.project.ddoreum.mountaininfo.detail

import androidx.lifecycle.viewModelScope
import com.project.ddoreum.core.BaseViewModel
import com.project.ddoreum.di.IoDispatcher
import com.project.ddoreum.domain.entity.mountain.MountainDetailInfoData
import com.project.ddoreum.domain.usecase.mountain.AddFavoriteMountainUseCase
import com.project.ddoreum.domain.usecase.mountain.DeleteFavoriteMountainUseCase
import com.project.ddoreum.domain.usecase.mountain.GetAllFavoriteMountainUseCase
import com.project.ddoreum.domain.usecase.mountain.GetMountainDetailInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MountainInfoDetailViewModel @Inject constructor(
    private val getMountainDetailInfoUseCase: GetMountainDetailInfoUseCase,
    private val getAllFavoriteMountainUseCase: GetAllFavoriteMountainUseCase,
    private val addFavoriteMountainUseCase: AddFavoriteMountainUseCase,
    private val deleteFavoriteMountainUseCase: DeleteFavoriteMountainUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): BaseViewModel() {

    private val _mountainDetailData = MutableStateFlow<MountainDetailInfoData?>(null)
    val mountainDetailData: StateFlow<MountainDetailInfoData?> get() = _mountainDetailData

    fun getMountainDetailInfo(name: String) {
        viewModelScope.launch(ioDispatcher) {
            val mountainDetailInfo = getMountainDetailInfoUseCase.invoke(name)
            val favoriteMountainList = getAllFavoriteMountainUseCase.invoke(Unit)
            combine(mountainDetailInfo, favoriteMountainList) { mountainDetailInfo, favoriteList ->
                if (favoriteList != null) {
                    _mountainDetailData.update {
                        mountainDetailInfo?.let {
                            val code = checkNotNull(it.mountainCode.toString())
                            it.copy(isFavorite = favoriteList.contains(code))
                        }
                    }
                } else {
                    _mountainDetailData.value = mountainDetailInfo
                }
            }.launchIn(viewModelScope)
        }
    }

    fun updateMountainFavoriteState() {
        viewModelScope.launch(ioDispatcher) {
            _mountainDetailData.value?.let {
                if (it.isFavorite) {
                    deleteFavoriteMountainUseCase.invoke(it)
                } else {
                    addFavoriteMountainUseCase.invoke(it)
                }
            }
        }
    }
}