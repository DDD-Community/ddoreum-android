package com.project.ddoreum.mountaininfo.list

import com.project.ddoreum.R
import com.project.ddoreum.core.BaseFragment
import com.project.ddoreum.core.BaseViewModel
import com.project.ddoreum.databinding.FragmentMountainInfoListBinding

class MountainInfoListFragment : BaseFragment<FragmentMountainInfoListBinding>(R.layout.fragment_mountain_info_list) {

    companion object {
        val TAG = this::class.java.toString()
        fun newInstance() = MountainInfoListFragment()
    }

    override val viewModel: BaseViewModel
        get() = TODO("Not yet implemented")

    override fun initLayout() {
    }
}