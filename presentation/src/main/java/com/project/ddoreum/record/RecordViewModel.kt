package com.project.ddoreum.record

import com.project.ddoreum.core.BaseViewModel
import com.project.ddoreum.model.Record
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecordViewModel @Inject constructor() : BaseViewModel() {
    
    val recordList: List<Record>
        get() = Record.defaultList

}