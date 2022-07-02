package com.project.ddoreum.mountaininfo.search

import com.project.ddoreum.R
import com.project.ddoreum.core.BaseFragment
import com.project.ddoreum.core.BaseViewModel
import com.project.ddoreum.databinding.FragmentMountainInfoSearchBinding

class MountainInfoSearchFragment : BaseFragment<FragmentMountainInfoSearchBinding>(R.layout.fragment_mountain_info_search) {

    companion object {
        val TAG = this::class.java.toString()
        fun newInstance() = MountainInfoSearchFragment()
    }

    override val viewModel: BaseViewModel
        get() = TODO("Not yet implemented")

    override fun initLayout() {
    }
}