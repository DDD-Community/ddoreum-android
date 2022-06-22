package com.project.ddoreum.mypage

import com.project.ddoreum.R
import com.project.ddoreum.core.BaseFragment
import com.project.ddoreum.databinding.FragmentMyPageBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyPageFragment : BaseFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {

    companion object {
        fun newInstance() = MyPageFragment()
    }

    override fun initView() {
    }
}