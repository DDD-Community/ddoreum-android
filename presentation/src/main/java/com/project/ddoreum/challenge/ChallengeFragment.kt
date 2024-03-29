package com.project.ddoreum.challenge

import android.content.Intent
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.project.ddoreum.R
import com.project.ddoreum.challenge.detail.ChallengeDetailActivity
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
        initRequestData()
        initRcvAdapter()
        collectPeriodListFlow()
        collectLocationListFlow()
        collectOnGoingChallengeListFlow()
    }

    private fun initRequestData() {
        viewModel.getAllChallengeList()
    }

    private fun initRcvAdapter() {
        binding.rcvPeriodChallenge.adapter = periodChallengeListAdapter
        binding.rcvLocationChallenge.adapter = locationChallengeListAdapter
        binding.rcvOnGoingChallenge.adapter = onGoingChallengeListAdapter
    }

    private fun collectPeriodListFlow() = lifecycleScope.launchWhenCreated {
        viewModel.periodChallengeList.collect {
            periodChallengeListAdapter.submitList(it)
        }
    }

    private fun collectLocationListFlow() = lifecycleScope.launchWhenCreated {
        viewModel.locationChallengeList.collect {
            locationChallengeListAdapter.submitList(it)
        }
    }

    private fun collectOnGoingChallengeListFlow() = lifecycleScope.launchWhenCreated {
        viewModel.inProgressChallengeData.collect {
            binding.cvEmptyChallenge.isVisible = it.isNullOrEmpty()
            onGoingChallengeListAdapter.submitList(it)
            if (it.isNullOrEmpty()) {
                binding.tvSuccessChallengeMsg.text = "진행중인 \n챌린지가 없습니당"
            } else {
                binding.tvSuccessChallengeMsg.text = "진행중인 \n${it.size}개의 챌린지가 있습니당"
            }
        }
    }

    private val periodChallengeListAdapter by lazy {
        PeriodChallengeListAdapter {
            val intent = Intent(requireContext(), ChallengeDetailActivity::class.java).apply {
                putExtra("challenge_id", it.id)
            }
            startActivity(intent)
        }
    }

    private val locationChallengeListAdapter by lazy {
        PeriodChallengeListAdapter {
            val intent = Intent(requireContext(), ChallengeDetailActivity::class.java).apply {
                putExtra("challenge_id", it.id)
            }
            startActivity(intent)
        }
    }

    private val onGoingChallengeListAdapter by lazy {
        OnGoingChallengeListAdapter {
            
        }
    }
}