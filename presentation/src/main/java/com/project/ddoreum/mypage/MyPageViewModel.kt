package com.project.ddoreum.mypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.project.ddoreum.BuildConfig
import com.project.ddoreum.core.BaseViewModel
import com.project.ddoreum.di.IoDispatcher
import com.project.ddoreum.di.MainDispatcher
import com.project.ddoreum.domain.usecase.intro.GetUserInfoUseCase
import com.project.ddoreum.model.UserInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : BaseViewModel() {

    private val _name = MutableStateFlow<String>("")
    val name: StateFlow<String>
        get() = _name

    private val _userInfo = MutableLiveData<UserInfo>()
    val userInfo: LiveData<UserInfo>
        get() = _userInfo

    private val _state = MutableStateFlow<MyPageState>(MyPageState.Init)
    val state: StateFlow<MyPageState>
        get() = _state

    private val _event = MutableSharedFlow<MyPageViewEvent>()
    val event: SharedFlow<MyPageViewEvent>
        get() = _event

    val appVersion: String by lazy {
        BuildConfig.VERSION_NAME
    }

    init {
        _userInfo.value = UserInfo.default
    }

    fun getUserName() {
        viewModelScope.launch(ioDispatcher) {
            getUserInfoUseCase.invoke(Unit).onEach { userInfo ->
                if (userInfo.first.isNotEmpty()) {
                    _name.emit(userInfo.first)
                } else {
                    _name.emit("산또오름")
                }
                _state.emit(MyPageState.UserInfo)
            }.launchIn(viewModelScope)
        }
    }

    fun onClickOpenSource() {
        viewModelScope.launch(mainDispatcher) {
            _event.emit(MyPageViewEvent.OpenSource)
        }
    }
}