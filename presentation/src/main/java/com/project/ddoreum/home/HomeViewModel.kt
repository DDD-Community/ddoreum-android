package com.project.ddoreum.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.project.ddoreum.core.BaseViewModel
import com.project.ddoreum.di.IoDispatcher
import com.project.ddoreum.di.MainDispatcher
import com.project.ddoreum.domain.entity.challenge.ChallengeInfoData
import com.project.ddoreum.domain.usecase.challenge.GetAllChallengeListUseCase
import com.project.ddoreum.model.MountainRecommend
import com.project.ddoreum.model.UserInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllChallengeListUseCase: GetAllChallengeListUseCase,
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

    // TODO : home list들 변경 필요
    private val _challengeList = MutableStateFlow<List<Int>>(emptyList())
    val challengeList: StateFlow<List<Int>>
        get() = _challengeList

    private val _recommendList = MutableStateFlow<List<MountainRecommend>>(emptyList())
    val recommendList: StateFlow<List<MountainRecommend>>
        get() = _recommendList

    private val _recommendChallengeList = MutableStateFlow<List<ChallengeInfoData>>(emptyList())
    val recommendChallengeList: StateFlow<List<ChallengeInfoData>>
        get() = _recommendChallengeList

    fun getRecommendedChallengeList() {
        // TODO : 챌린지 리스트 가져와서 랜덤으로 뿌릴 예정
//        viewModelScope.launch(ioDispatcher) {
//            getAllChallengeListUseCase.invoke(CHALLENGE_KEY).onEach { data ->
//                _recommendChallengeList.update { data }
//            }.launchIn(viewModelScope)
//        }
    }

    init {
        _name.value = "산또오름"
        _userInfo.value = UserInfo.default

        // TODO : 더미데이터 삭제
        _challengeList.value = listOf(20, 30, 50, 100)
        _recommendList.value = MountainRecommend.defaultList
        _recommendChallengeList.value = ChallengeInfoData.default
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