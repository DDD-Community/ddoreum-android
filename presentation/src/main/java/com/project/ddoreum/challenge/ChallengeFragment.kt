package com.project.ddoreum.challenge

import com.project.ddoreum.R
import com.project.ddoreum.core.BaseFragment
import com.project.ddoreum.core.BaseViewModel
import com.project.ddoreum.databinding.FragmentChallengeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChallengeFragment : BaseFragment<FragmentChallengeBinding>(R.layout.fragment_challenge) {

    companion object {
        @JvmStatic
        fun newInstance() = ChallengeFragment()
    }

    override val viewModel: BaseViewModel
        get() = TODO("Not yet implemented")

    override fun initLayout() {
    }
}