package com.project.ddoreum.challenge

import androidx.lifecycle.viewModelScope
import com.project.ddoreum.core.BaseViewModel
import com.project.ddoreum.di.IoDispatcher
import com.project.ddoreum.domain.entity.challenge.ChallengeInfoData
import com.project.ddoreum.domain.usecase.challenge.GetAllChallengeListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChallengeViewModel @Inject constructor(
    private val getAllChallengeListUseCase: GetAllChallengeListUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): BaseViewModel() {

    private val _periodChallengeList = MutableStateFlow<List<ChallengeInfoData>>(emptyList())
    val periodChallengeList: StateFlow<List<ChallengeInfoData>> get() = _periodChallengeList

    private val _locationChallengeList = MutableStateFlow<List<ChallengeInfoData>>(emptyList())
    val locationChallengeList: StateFlow<List<ChallengeInfoData>> get() = _locationChallengeList

    fun getAllChallengeList() {
        viewModelScope.launch(ioDispatcher) {
            getAllChallengeListUseCase.invoke(CHALLENGE_KEY).onEach { data ->
                val periodList = mutableListOf<ChallengeInfoData>()
                val locationList = mutableListOf<ChallengeInfoData>()
                data.forEach {
                    when(it.type) {
                        PERIOD -> periodList.add(it)
                        LOCATION -> locationList.add(it)
                    }
                }
                _periodChallengeList.update { periodList }
                _locationChallengeList.update { locationList }
            }.launchIn(viewModelScope)
        }
    }

    companion object {
        private val CHALLENGE_KEY = "challenge_list"
        private const val PERIOD = "PERIOD"
        private const val LOCATION = "LOCATION"
    }
}