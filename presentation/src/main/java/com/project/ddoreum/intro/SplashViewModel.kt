package com.project.ddoreum.intro

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.project.ddoreum.core.BaseViewModel
import com.project.ddoreum.di.MainDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class SplashViewModel @Inject constructor(
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher
) : BaseViewModel() {

    private val _titleState = MutableLiveData<Boolean>()
    val titleState: LiveData<Boolean>
        get() = _titleState

    private val _state = MutableStateFlow<SplashState>(SplashState.Init)
    val state: StateFlow<SplashState>
        get() = _state

    fun initSplash() {
        _titleState.value = false
        viewModelScope.launch(mainDispatcher) {
            delay(500)

            _state.emit(SplashState.Permission)
        }
    }

    fun setPermissionCompleted() {
        _titleState.value = true
        viewModelScope.launch(mainDispatcher) {
            _state.emit(SplashState.Login)
        }
    }

    fun setPermissionRejected() {
        viewModelScope.launch(mainDispatcher) {
            _state.emit(SplashState.RejectPermission)
        }
    }

    // TODO : 로그인 로직으로 이동해야 함
    // 우선 메인액티비티로 이동
    fun onClickLogin() {
        viewModelScope.launch(mainDispatcher) {
            _state.emit(SplashState.Finish)
        }
    }
}