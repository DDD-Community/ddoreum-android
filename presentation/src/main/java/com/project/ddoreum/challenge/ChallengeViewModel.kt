package com.project.ddoreum.challenge

import androidx.lifecycle.viewModelScope
import com.project.ddoreum.common.getYearAfterDate
import com.project.ddoreum.core.BaseViewModel
import com.project.ddoreum.di.IoDispatcher
import com.project.ddoreum.domain.entity.challenge.ChallengeInfoData
import com.project.ddoreum.domain.entity.challenge.InProgressChallengeData
import com.project.ddoreum.domain.usecase.challenge.GetAllChallengeListUseCase
import com.project.ddoreum.domain.usecase.challenge.GetAllINProgressChallengeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChallengeViewModel @Inject constructor(
    private val getAllChallengeListUseCase: GetAllChallengeListUseCase,
    private val getAllINProgressChallengeUseCase: GetAllINProgressChallengeUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): BaseViewModel() {

    private val _periodChallengeList = MutableStateFlow<List<ChallengeInfoData>>(emptyList())
    val periodChallengeList: StateFlow<List<ChallengeInfoData>> get() = _periodChallengeList

    private val _locationChallengeList = MutableStateFlow<List<ChallengeInfoData>>(emptyList())
    val locationChallengeList: StateFlow<List<ChallengeInfoData>> get() = _locationChallengeList

    private val _inProgressChallengeData = MutableStateFlow<List<InProgressChallengeData>>(emptyList())
    val inProgressChallengeData: StateFlow<List<InProgressChallengeData>> get() = _inProgressChallengeData

    fun getAllChallengeList() {
        viewModelScope.launch(ioDispatcher) {
            val inProgressData = getAllINProgressChallengeUseCase.invoke(Unit) ?: emptyFlow()
            val allChallengeData = getAllChallengeListUseCase.invoke(CHALLENGE_KEY)
            combine(inProgressData, allChallengeData) { inProgress, allChallenge ->
                val periodList = mutableListOf<ChallengeInfoData>()
                val locationList = mutableListOf<ChallengeInfoData>()
                val inProgressList = mutableListOf<InProgressChallengeData>()
                allChallenge.forEach {
                    if (inProgress.containsKey(it.id)) {
                        createInProgressList(inProgressList, it, inProgress[it.id]?.second ?: 0, inProgress[it.id]?.third ?: "")
                    }
                    when(it.type) {
                        PERIOD -> periodList.add(it)
                        LOCATION -> locationList.add(it)
                    }
                }
                _periodChallengeList.update { periodList }
                _locationChallengeList.update { locationList }
                _inProgressChallengeData.update { inProgressList }
            }.launchIn(viewModelScope)
        }
    }

    private fun createInProgressList(mutableList: MutableList<InProgressChallengeData>, data: ChallengeInfoData, succeedCount: Int, startDate: String) {
        mutableList.add(
            InProgressChallengeData(
                name = data.name,
                id = data.id,
                type = data.type,
                count = data.count,
                verifyList = data.verifyList,
                verifyCount = data.verifyCount,
                verifyPeriod = data.verifyPeriod,
                succeedCount = succeedCount,
                startDate = startDate,
                endDate = getYearAfterDate(startDate, 1)
            )
        )
    }

    companion object {
        private val CHALLENGE_KEY = "challenge_list"
        private const val PERIOD = "PERIOD"
        private const val LOCATION = "LOCATION"
    }
}