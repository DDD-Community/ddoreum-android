package com.project.ddoreum.home.certify

import androidx.lifecycle.SavedStateHandle
import com.project.ddoreum.core.BaseViewModel
import com.project.ddoreum.di.MainDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

@HiltViewModel
class HomeCertifyViewModel @Inject constructor(
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    private val _state = MutableSharedFlow<HomeCertifyState>()
    val state: SharedFlow<HomeCertifyState>
        get() = _state


}