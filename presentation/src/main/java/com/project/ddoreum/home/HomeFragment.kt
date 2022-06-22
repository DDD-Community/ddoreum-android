package com.project.ddoreum.home

import com.project.ddoreum.R
import com.project.ddoreum.core.BaseFragment
import com.project.ddoreum.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun initView() {
    }
}