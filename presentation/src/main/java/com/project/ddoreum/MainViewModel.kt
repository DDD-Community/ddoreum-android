package com.project.ddoreum

import androidx.lifecycle.viewModelScope
import com.project.ddoreum.core.BaseViewModel
import com.project.ddoreum.di.MainDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher
) : BaseViewModel() {

    private val _event = MutableSharedFlow<MainViewEvent>()
    val event: SharedFlow<MainViewEvent>
        get() = _event

    fun setChallengeTab() {
        viewModelScope.launch(mainDispatcher) {
            _event.emit(MainViewEvent.ClickChallenge)
        }
    }

}