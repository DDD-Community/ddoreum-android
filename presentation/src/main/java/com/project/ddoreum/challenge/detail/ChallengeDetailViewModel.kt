package com.project.ddoreum.challenge.detail

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
class ChallengeDetailViewModel @Inject constructor(
    private val getAllChallengeListUseCase: GetAllChallengeListUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : BaseViewModel() {

    private val _challengeDetailData = MutableStateFlow<ChallengeInfoData?>(null)
    val challengeDetailData: StateFlow<ChallengeInfoData?> get() = _challengeDetailData

    fun getChallengeDetailInfo(id: Int) {
        viewModelScope.launch(ioDispatcher) {
            getAllChallengeListUseCase.invoke("challenge_list").onEach {
                val result = it.find {
                    it.id == id
                }
                _challengeDetailData.update { result }
            }.launchIn(viewModelScope)
        }
    }

}