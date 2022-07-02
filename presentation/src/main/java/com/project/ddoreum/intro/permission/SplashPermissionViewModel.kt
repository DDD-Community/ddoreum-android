package com.project.ddoreum.intro.permission

import androidx.lifecycle.viewModelScope
import com.project.ddoreum.core.BaseViewModel
import com.project.ddoreum.di.MainDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class SplashPermissionViewModel @Inject constructor(
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher
) : BaseViewModel() {

    private val _state = MutableStateFlow<SplashPermissionState>(SplashPermissionState.Init)
    val state: StateFlow<SplashPermissionState>
        get() = _state

    fun onClickConfirm() {
        viewModelScope.launch(mainDispatcher) {
            _state.emit(SplashPermissionState.Permission)
        }
    }

    fun rejectCheckPermission() {
        viewModelScope.launch(mainDispatcher) {
            _state.emit(SplashPermissionState.RejectPermission)
        }
    }

    fun finishCheckPermission() {
        viewModelScope.launch(mainDispatcher) {
            _state.emit(SplashPermissionState.Finish)
        }
    }
}
