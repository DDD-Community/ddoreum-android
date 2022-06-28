package com.project.ddoreum.intro.permission

import androidx.lifecycle.viewModelScope
import com.project.ddoreum.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class SplashPermissionViewModel @Inject constructor() : BaseViewModel() {

    private val _state = MutableStateFlow<SplashPermissionState>(SplashPermissionState.Init)
    val state: StateFlow<SplashPermissionState>
        get() = _state

    fun onClickConfirm() {
        viewModelScope.launch {
            _state.emit(SplashPermissionState.Permission)
        }
    }

    fun rejectCheckPermission() {
        viewModelScope.launch {
            _state.emit(SplashPermissionState.RejectPermission)
        }
    }

    fun finishCheckPermission() {
        viewModelScope.launch {
            _state.emit(SplashPermissionState.Finish)
        }
    }
}
