package com.project.ddoreum.record

import com.project.ddoreum.R
import com.project.ddoreum.core.BaseFragment
import com.project.ddoreum.core.BaseViewModel
import com.project.ddoreum.databinding.FragmentRecordBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecordFragment : BaseFragment<FragmentRecordBinding>(R.layout.fragment_record) {

    companion object {
        fun newInstance() = RecordFragment()
    }

    override val viewModel: BaseViewModel
        get() = TODO("Not yet implemented")

    override fun initLayout() {
    }
}