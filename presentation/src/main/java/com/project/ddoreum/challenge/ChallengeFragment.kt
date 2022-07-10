package com.project.ddoreum.challenge

import android.content.Intent
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.project.ddoreum.R
import com.project.ddoreum.challenge.detail.ChallengeDetailActivity
import com.project.ddoreum.core.BaseFragment
import com.project.ddoreum.databinding.FragmentChallengeBinding
import com.project.ddoreum.mountaininfo.detail.MountainInfoDetailActivity
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
    }

    private fun initRequestData() {
        viewModel.getAllChallengeList()
    }

    private fun initRcvAdapter() {
        binding.rcvPeriodChallenge.adapter = periodChallengeListAdapter
        binding.rcvLocationChallenge.adapter = locationChallengeListAdapter
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

    private val periodChallengeListAdapter by lazy {
        PeriodChallengeListAdapter {
            val intent = Intent(activity, ChallengeDetailActivity::class.java).apply {
                putExtra("challenge_id", it.id)
            }
            startActivity(intent)
        }
    }

    private val locationChallengeListAdapter by lazy {
        PeriodChallengeListAdapter {
            val intent = Intent(activity, ChallengeDetailActivity::class.java).apply {
                putExtra("challenge_id", it.id)
            }
            startActivity(intent)
        }
    }
}