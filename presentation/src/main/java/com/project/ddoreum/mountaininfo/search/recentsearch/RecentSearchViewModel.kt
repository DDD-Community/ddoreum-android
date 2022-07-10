package com.project.ddoreum.mountaininfo.search.recentsearch

import androidx.lifecycle.viewModelScope
import com.project.ddoreum.core.BaseViewModel
import com.project.ddoreum.di.IoDispatcher
import com.project.ddoreum.domain.usecase.search.DeleteRecentSearchKeywordUseCase
import com.project.ddoreum.domain.usecase.search.GetAllRecentSearchKeywordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecentSearchViewModel @Inject constructor(
    private val getAllRecentSearchKeywordUseCase: GetAllRecentSearchKeywordUseCase,
    private val deleteRecentSearchKeywordUseCase: DeleteRecentSearchKeywordUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): BaseViewModel() {

    private val _allRecentKeywordList = MutableStateFlow<List<String>>(listOf())
    val allRecentKeywordList: StateFlow<List<String>> get() = _allRecentKeywordList

    fun getAllRecentKeywordList() {
        viewModelScope.launch(ioDispatcher) {
            getAllRecentSearchKeywordUseCase.invoke(Unit).onEach { data ->
                if (data != null) {
                    _allRecentKeywordList.update {
                        data.toList()
                    }
                } else {
                    _allRecentKeywordList.value = emptyList()
                }

            }.launchIn(viewModelScope)
        }
    }

    fun deleteRecentKeyword(keyword: String) {
        viewModelScope.launch(ioDispatcher) {
            deleteRecentSearchKeywordUseCase.invoke(keyword)
        }
    }

    fun deleteAllRecentKeyword() {
        viewModelScope.launch(ioDispatcher) {
            _allRecentKeywordList.value.forEach {
                deleteRecentSearchKeywordUseCase.invoke(it)
            }
        }
    }

}