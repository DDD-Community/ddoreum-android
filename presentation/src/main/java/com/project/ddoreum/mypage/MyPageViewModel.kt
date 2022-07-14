package com.project.ddoreum.mypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.project.ddoreum.BuildConfig
import com.project.ddoreum.core.BaseViewModel
import com.project.ddoreum.di.MainDispatcher
import com.project.ddoreum.model.UserInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

@HiltViewModel
class MyPageViewModel @Inject constructor(
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher,
) : BaseViewModel() {

    private val _name = MutableLiveData<String>()
    val name: LiveData<String>
        get() = _name

    private val _userInfo = MutableLiveData<UserInfo>()
    val userInfo: LiveData<UserInfo>
        get() = _userInfo

    private val _state = MutableSharedFlow<MyPageState>()
    val state: SharedFlow<MyPageState>
        get() = _state

    val appVersion: String by lazy {
        BuildConfig.VERSION_NAME
    }

    init {
        _name.value = "또오름"
        _userInfo.value = UserInfo.default
    }

    fun onClickOpenSource() {
        viewModelScope.launch(mainDispatcher) {
            _state.emit(MyPageState.OpenSource)
        }
    }


}