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
import com.project.ddoreum.domain.entity.mountain.MountainInfoData
import com.project.ddoreum.domain.usecase.challenge.GetAllChallengeListUseCase
import com.project.ddoreum.domain.usecase.challenge.GetAllINProgressChallengeUseCase
import com.project.ddoreum.domain.usecase.intro.GetUserInfoUseCase
import com.project.ddoreum.domain.usecase.mountain.GetAllMountainInfoUseCase
import com.project.ddoreum.model.UserInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllChallengeListUseCase: GetAllChallengeListUseCase,
    private val getAllINProgressChallengeUseCase: GetAllINProgressChallengeUseCase,
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val getAllMountainInfoUseCase: GetAllMountainInfoUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher
) : BaseViewModel() {

    private val _name = MutableStateFlow<String>("")
    val name: StateFlow<String>
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

    private val _recommendChallengeList = MutableStateFlow<List<ChallengeInfoData>>(emptyList())
    val recommendChallengeList: StateFlow<List<ChallengeInfoData>>
        get() = _recommendChallengeList

    private val _inProgressChallengeData =
        MutableStateFlow<List<InProgressChallengeData>>(emptyList())
    val inProgressChallengeData: StateFlow<List<InProgressChallengeData>>
        get() = _inProgressChallengeData

    private val _recommendedMountainList = MutableStateFlow<List<MountainInfoData>>(emptyList())
    val recommendedMountainList: StateFlow<List<MountainInfoData>>
        get() = _recommendedMountainList


    private fun getInProgressChallengeList() {
        viewModelScope.launch(ioDispatcher) {
            val inProgressData = getAllINProgressChallengeUseCase.invoke(Unit) ?: emptyFlow()
            val allChallengeData =
                getAllChallengeListUseCase.invoke(ChallengeViewModel.CHALLENGE_KEY)
            combine(inProgressData, allChallengeData) { inProgress, allChallenge ->
                val inProgressList = mutableListOf<InProgressChallengeData>()
                allChallenge.forEach {
                    if (inProgress.containsKey(it.id)) {
                        createInProgressList(
                            inProgressList,
                            it,
                            inProgress[it.id]?.second ?: 0,
                            inProgress[it.id]?.third ?: ""
                        )
                    }
                }
                _recommendChallengeList.update { allChallenge.shuffled() }
                _inProgressChallengeData.update { inProgressList }
            }.launchIn(viewModelScope)
        }
    }

    private fun createInProgressList(
        mutableList: MutableList<InProgressChallengeData>,
        data: ChallengeInfoData,
        succeedCount: Int,
        startDate: String
    ) {
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
        _userInfo.value = UserInfo.default

        _recommendChallengeList.value = ChallengeInfoData.default
        getInProgressChallengeList()
    }

    fun getUserName() {
        viewModelScope.launch(ioDispatcher) {
            getUserInfoUseCase.invoke(Unit).onEach { userInfo ->
                if (userInfo.first.isNotEmpty()) {
                    _name.emit(userInfo.first)
                } else {
                    _name.emit("산또오름")
                }
                _state.emit(HomeState.UserInfo)
            }.launchIn(viewModelScope)
        }
    }

    fun getRecommendedMountainData() {
        viewModelScope.launch(ioDispatcher) {
            getAllMountainInfoUseCase.invoke(Unit).onEach {
                it?.filter {
                    !it.mountainInfo.mountainImage.isNullOrBlank()
                            && !it.mountainInfo.subTitle.isNullOrBlank()
                }?.let {
                    val randomList = it.shuffled().subList(0, 3)
                    _recommendedMountainList.emit(randomList)
                }
            }.launchIn(viewModelScope)
        }
    }

    fun onClickCertButton() {
        viewModelScope.launch(mainDispatcher) {
            _event.emit(HomeViewEvent.ClickCert)
        }
    }

    fun onClickChallenge() {
        viewModelScope.launch(mainDispatcher) {
            _event.emit(HomeViewEvent.ClickChallenge)
        }
    }
}