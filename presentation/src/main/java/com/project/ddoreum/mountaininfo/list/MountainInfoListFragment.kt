package com.project.ddoreum.mountaininfo.list

import androidx.fragment.app.viewModels
import com.project.ddoreum.R
import com.project.ddoreum.common.repeatCallDefaultOnCreated
import com.project.ddoreum.core.BaseFragment
import com.project.ddoreum.databinding.FragmentMountainInfoListBinding
import com.project.ddoreum.mountaininfo.MountainInfoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MountainInfoListFragment : BaseFragment<FragmentMountainInfoListBinding>(R.layout.fragment_mountain_info_list) {

    companion object {
        val TAG = this::class.java.toString()
        fun newInstance() = MountainInfoListFragment()
    }

    override val viewModel: MountainInfoListViewModel by viewModels()

    private val sharedViewModel: MountainInfoViewModel by viewModels({requireParentFragment()})

    override fun initLayout() {
        viewLifecycleOwner.repeatCallDefaultOnCreated {
            sharedViewModel.mountainList.collect {

            }
        }
    }
}