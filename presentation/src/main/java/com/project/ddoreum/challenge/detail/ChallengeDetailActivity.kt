package com.project.ddoreum.challenge.detail

import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.project.ddoreum.R
import com.project.ddoreum.core.BaseActivity
import com.project.ddoreum.databinding.ActivityChallengeDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChallengeDetailActivity :
    BaseActivity<ActivityChallengeDetailBinding>(R.layout.activity_challenge_detail) {

    override val viewModel: ChallengeDetailViewModel by viewModels()

    override fun initLayout() {
        bind {
            viewmodel = viewModel
            lifecycleOwner = this@ChallengeDetailActivity
        }
        initRequestData()
        initRcvAdapter()
        initCollectFlow()
    }

    private fun initRequestData() {
        val id = intent.getIntExtra("challenge_id", -1)
        if (id != -1) {
            viewModel.getChallengeDetailInfo(id)
        }
    }

    private fun initRcvAdapter() {
        binding.rcvVerifyMountain.adapter = verifyMountainListAdapter
    }

    private fun initCollectFlow() = lifecycleScope.launchWhenCreated {
        viewModel.challengeDetailData.collect {
            it?.let {
                verifyMountainListAdapter.submitList(it.verifyList)
            }
        }
    }

    private val verifyMountainListAdapter by lazy {
        ChallengeVerifyMountainListAdapter {

        }
    }

    companion object {
        fun newInstance(id: Int): ChallengeDetailActivity {
            return ChallengeDetailActivity().apply {
                intent.putExtra("challenge_id", id)
            }
        }
    }
}