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

    private val _challengeList = MutableStateFlow<List<ChallengeInfoData>>(emptyList())
    val challengeList: StateFlow<List<ChallengeInfoData>> get() = _challengeList

    fun getAllChallengeList() {
        viewModelScope.launch(ioDispatcher) {
            getAllChallengeListUseCase.invoke(CHALLENGE_KEY).onEach { data ->
                _challengeList.update {
                    data
                }
            }.launchIn(viewModelScope)
        }
    }

    companion object {
        private val CHALLENGE_KEY = "challenge_list"
    }
}