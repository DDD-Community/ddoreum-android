package com.project.ddoreum.challenge

import com.project.ddoreum.R
import com.project.ddoreum.core.BaseFragment
import com.project.ddoreum.databinding.FragmentChallengeBinding


class ChallengeFragment : BaseFragment<FragmentChallengeBinding>(R.layout.fragment_challenge) {

    companion object {
        @JvmStatic
        fun newInstance() = ChallengeFragment()
    }
}