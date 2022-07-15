package com.project.ddoreum.challenge.detail

import androidx.lifecycle.viewModelScope
import com.project.ddoreum.core.BaseViewModel
import com.project.ddoreum.di.IoDispatcher
import com.project.ddoreum.domain.entity.challenge.ChallengeInfoData
import com.project.ddoreum.domain.entity.challenge.RequestInProgressChallenge
import com.project.ddoreum.domain.usecase.challenge.AddInProgressChallengeUseCase
import com.project.ddoreum.domain.usecase.challenge.GetAllChallengeListUseCase
import com.project.ddoreum.domain.usecase.challenge.GetAllINProgressChallengeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChallengeDetailViewModel @Inject constructor(
    private val getAllChallengeListUseCase: GetAllChallengeListUseCase,
    private val getAllInProgressChallengeUseCase: GetAllINProgressChallengeUseCase,
    private val addInProgressChallengeUseCase: AddInProgressChallengeUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : BaseViewModel() {

    private val _challengeDetailData = MutableStateFlow<ChallengeInfoData?>(null)
    val challengeDetailData: StateFlow<ChallengeInfoData?> get() = _challengeDetailData

    private val _inProgressState = MutableStateFlow<Boolean>(false)
    val inProgressState: StateFlow<Boolean> get() = _inProgressState

    fun getChallengeDetailInfo(id: Int) {
        viewModelScope.launch(ioDispatcher) {
            val inProgressChallengeList = getAllInProgressChallengeUseCase.invoke(Unit) ?: emptyFlow()
            val allChallengeList = getAllChallengeListUseCase.invoke("challenge_list")
            combine(inProgressChallengeList, allChallengeList) { inProgress, challenge ->
                val result = challenge.find {
                    it.id == id
                }
                _inProgressState.update { inProgress.containsKey(id) }
                _challengeDetailData.update { result }
            }.launchIn(viewModelScope)
        }
    }

    fun addChallenge() {
        viewModelScope.launch(ioDispatcher) {
            val challenge = _challengeDetailData.value
            addInProgressChallengeUseCase.invoke(
                RequestInProgressChallenge(
                    challengeId = challenge?.id ?: 0,
                    challengeType = challenge?.type ?: "",
                    succeedCount = 0
                )
            )
        }
    }

}