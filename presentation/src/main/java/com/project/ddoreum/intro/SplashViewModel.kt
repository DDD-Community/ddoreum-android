package com.project.ddoreum.intro

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.project.ddoreum.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class SplashViewModel @Inject constructor() : BaseViewModel() {

    private val _titleState = MutableLiveData<Boolean>()
    val titleState: LiveData<Boolean>
        get() = _titleState

    private val _state = MutableStateFlow<SplashState>(SplashState.Init)
    val state: MutableStateFlow<SplashState>
        get() = _state

    fun initSplash() {
        _titleState.value = false
        viewModelScope.launch {
            delay(1000)

            _state.emit(SplashState.Permission)
        }
    }

    fun setPermissionCompleted() {
        _state.value = SplashState.Finish
    }

}