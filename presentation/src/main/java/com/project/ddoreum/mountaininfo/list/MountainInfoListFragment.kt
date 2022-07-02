package com.project.ddoreum.mountaininfo.list

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.ddoreum.R
import com.project.ddoreum.common.repeatCallDefaultOnCreated
import com.project.ddoreum.core.BaseFragment
import com.project.ddoreum.databinding.FragmentMountainInfoListBinding
import com.project.ddoreum.mountaininfo.MountainInfoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MountainInfoListFragment :
    BaseFragment<FragmentMountainInfoListBinding>(R.layout.fragment_mountain_info_list) {

    companion object {
        val TAG = this::class.java.toString()
        fun newInstance() = MountainInfoListFragment()
    }

    override val viewModel: MountainInfoListViewModel by viewModels()

    private val sharedViewModel: MountainInfoViewModel by viewModels({ requireParentFragment() })

    override fun initLayout() {
        initRcvView()
        observeFlow()
    }

    private fun initRcvView() = with(binding) {
        rcvMountainInfoList.adapter = mountainInfoListAdapter
        rcvMountainInfoList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    private fun observeFlow() = viewLifecycleOwner.repeatCallDefaultOnCreated {
        sharedViewModel.mountainList.collect {
            mountainInfoListAdapter.submitList(it)
        }
    }

    private val mountainInfoListAdapter by lazy {
        MountainInfoListAdapter {

        }
    }
}