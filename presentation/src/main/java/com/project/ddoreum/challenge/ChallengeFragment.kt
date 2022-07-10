package com.project.ddoreum.challenge

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.project.ddoreum.R
import com.project.ddoreum.core.BaseFragment
import com.project.ddoreum.databinding.FragmentChallengeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChallengeFragment : BaseFragment<FragmentChallengeBinding>(R.layout.fragment_challenge) {

    companion object {
        @JvmStatic
        fun newInstance() = ChallengeFragment()
    }

    override val viewModel: ChallengeViewModel by viewModels()

    override fun initLayout() {
        viewModel.getAllChallengeList()
        collectFlow()
    }

    private fun collectFlow() = lifecycleScope.launchWhenCreated {
        viewModel.challengeList.collect {
            it.forEach { data ->
            }
        }
    }
}