package com.project.ddoreum.mountaininfo.search

import androidx.fragment.app.viewModels
import com.project.ddoreum.R
import com.project.ddoreum.core.BaseFragment
import com.project.ddoreum.databinding.FragmentMountainInfoSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MountainInfoSearchFragment : BaseFragment<FragmentMountainInfoSearchBinding>(R.layout.fragment_mountain_info_search) {

    companion object {
        val TAG = this::class.java.toString()
        fun newInstance() = MountainInfoSearchFragment()
    }

    override val viewModel: MountainInfoSearchViewModel by viewModels()

    override fun initLayout() {
    }
}