package com.project.ddoreum.intro

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.project.ddoreum.core.BaseViewModel
import com.project.ddoreum.di.MainDispatcher
import com.project.ddoreum.domain.usecase.intro.GetUserInfoUseCase
import com.project.ddoreum.domain.usecase.intro.SetUserInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val setUserInfoUseCase: SetUserInfoUseCase,
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
            delay(1000)

            _state.emit(SplashState.Permission)
        }
    }

    fun setPermissionCompleted() {
        viewModelScope.launch(mainDispatcher) {
            _state.emit(SplashState.SuccessPermission)
        }
    }

    fun setPermissionRejected() {
        viewModelScope.launch(mainDispatcher) {
            _state.emit(SplashState.RejectPermission)
        }
    }

    suspend fun getUserInfoLoggedIn() {
        viewModelScope.launch {
            getUserInfoUseCase.invoke(Unit).onEach {
                if (it.first.isNotEmpty()) {
                    _state.emit(SplashState.Finish(it.first))
                } else {
                    _titleState.value = true
                }
            }.launchIn(viewModelScope)
        }
    }

    fun handleUserInformation(data: Task<GoogleSignInAccount>) {
        runCatching {
            Timber.d("data received")
            data.result
        }.onSuccess {
            Timber.d("data received Success")
            // TODO : 또오름 Login API 연동
            viewModelScope.launch(mainDispatcher) {

                setUserInfoUseCase.invoke(
                    Triple(it.displayName ?: "", it.email ?: "", it.photoUrl.toString())
                )

                _state.emit(
                    SplashState.Finish(it.displayName ?: "또오름")
                )
            }
        }.onFailure {
            Timber.d("data received Failed")
            viewModelScope.launch(mainDispatcher) {
                _state.emit(SplashState.FailLogin)
            }
        }
    }

    fun onClickLogin() {
        viewModelScope.launch(mainDispatcher) {
            _state.emit(SplashState.Login)
        }
    }
}
