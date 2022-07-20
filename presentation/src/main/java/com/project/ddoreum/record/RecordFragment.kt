package com.project.ddoreum.record

import androidx.fragment.app.viewModels
import com.project.ddoreum.R
import com.project.ddoreum.core.BaseFragment
import com.project.ddoreum.databinding.FragmentRecordBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecordFragment : BaseFragment<FragmentRecordBinding>(R.layout.fragment_record) {

    override val viewModel: RecordViewModel by viewModels()

    override fun initLayout() {
        bind {
            vm = viewModel
            lifecycleOwner = viewLifecycleOwner

            list.adapter = RecordAdapter()
        }
    }

    companion object {
        fun newInstance(): RecordFragment {
            return RecordFragment()
        }
    }
}