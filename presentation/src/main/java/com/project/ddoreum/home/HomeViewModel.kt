package com.project.ddoreum.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.project.ddoreum.challenge.ChallengeViewModel
import com.project.ddoreum.common.getYearAfterDate
import com.project.ddoreum.core.BaseViewModel
import com.project.ddoreum.di.IoDispatcher
import com.project.ddoreum.di.MainDispatcher
import com.project.ddoreum.domain.entity.challenge.ChallengeInfoData
import com.project.ddoreum.domain.entity.challenge.InProgressChallengeData
import com.project.ddoreum.domain.usecase.challenge.GetAllChallengeListUseCase
import com.project.ddoreum.domain.usecase.challenge.GetAllINProgressChallengeUseCase
import com.project.ddoreum.model.MountainRecommend
import com.project.ddoreum.model.UserInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllChallengeListUseCase: GetAllChallengeListUseCase,
    private val getAllINProgressChallengeUseCase: GetAllINProgressChallengeUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher
) : BaseViewModel() {

    private val _name = MutableLiveData<String>()
    val name: LiveData<String>
        get() = _name

    private val _event = MutableSharedFlow<HomeViewEvent>()
    val event: SharedFlow<HomeViewEvent>
        get() = _event

    private val _state = MutableStateFlow<HomeState>(HomeState.Init)
    val state: StateFlow<HomeState>
        get() = _state

    private val _userInfo = MutableLiveData<UserInfo>()
    val userInfo: LiveData<UserInfo>
        get() = _userInfo

    private val _recommendList = MutableStateFlow<List<MountainRecommend>>(emptyList())
    val recommendList: StateFlow<List<MountainRecommend>>
        get() = _recommendList

    private val _recommendChallengeList = MutableStateFlow<List<ChallengeInfoData>>(emptyList())
    val recommendChallengeList: StateFlow<List<ChallengeInfoData>>
        get() = _recommendChallengeList

    private val _inProgressChallengeData = MutableStateFlow<List<InProgressChallengeData>>(emptyList())
    val inProgressChallengeData: StateFlow<List<InProgressChallengeData>> get() = _inProgressChallengeData

    fun getRecommendedChallengeList() {
        // TODO : 챌린지 리스트 가져와서 랜덤으로 뿌릴 예정
//        viewModelScope.launch(ioDispatcher) {
//            getAllChallengeListUseCase.invoke(CHALLENGE_KEY).onEach { data ->
//                _recommendChallengeList.update { data }
//            }.launchIn(viewModelScope)
//        }
    }

    private fun getInProgressChallengeList() {
        viewModelScope.launch(ioDispatcher) {
            val inProgressData = getAllINProgressChallengeUseCase.invoke(Unit) ?: emptyFlow()
            val allChallengeData = getAllChallengeListUseCase.invoke(ChallengeViewModel.CHALLENGE_KEY)
            combine(inProgressData, allChallengeData) { inProgress, allChallenge ->
                val inProgressList = mutableListOf<InProgressChallengeData>()
                allChallenge.forEach {
                    if (inProgress.containsKey(it.id)) {
                        createInProgressList(inProgressList, it, inProgress[it.id]?.second ?: 0, inProgress[it.id]?.third ?: "")
                    }
                }
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

    init {
        _name.value = "산또오름"
        _userInfo.value = UserInfo.default

        // TODO : 더미데이터 삭제
        _recommendList.value = MountainRecommend.defaultList
        _recommendChallengeList.value = ChallengeInfoData.default
        getInProgressChallengeList()
    }

    fun onClickCertButton() {
        viewModelScope.launch(mainDispatcher) {
            _event.emit(HomeViewEvent.ClickCert)
        }
    }


    companion object {
        private val CHALLENGE_KEY = "challenge_list"
    }

}