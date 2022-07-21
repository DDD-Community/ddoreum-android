package com.project.ddoreum.home

import android.content.Intent
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearSnapHelper
import com.project.ddoreum.MainViewModel
import com.project.ddoreum.R
import com.project.ddoreum.challenge.OnGoingChallengeListAdapter
import com.project.ddoreum.challenge.detail.ChallengeDetailActivity
import com.project.ddoreum.common.hikingprogress.HikingProgressAdapter
import com.project.ddoreum.common.repeatCallDefaultOnStarted
import com.project.ddoreum.core.BaseFragment
import com.project.ddoreum.databinding.FragmentHomeBinding
import com.project.ddoreum.di.MainDispatcher
import com.project.ddoreum.home.certify.HomeCertifyBottomSheet
import com.project.ddoreum.home.newchallenge.HomeChallengeRecommendAdapter
import com.project.ddoreum.home.recommend.HomeMountainRecommendAdapter
import com.project.ddoreum.mountaininfo.detail.MountainInfoDetailActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    @Inject
    @MainDispatcher
    lateinit var mainDispatcher: CoroutineDispatcher

    override val viewModel: HomeViewModel by viewModels()

    private val activityViewModel: MainViewModel by activityViewModels()

    override fun initLayout() {
        bind {
            vm = viewModel
            lifecycleOwner = viewLifecycleOwner

            progressList.adapter = HikingProgressAdapter()

            challengeList.apply {
                LinearSnapHelper().attachToRecyclerView(this)
                adapter = onGoingChallengeListAdapter
            }

            recommendList.apply {
                LinearSnapHelper().attachToRecyclerView(this)
                adapter = mountainRecommendAdapter
            }

            challengeRecommendList.apply {
                LinearSnapHelper().attachToRecyclerView(this)
                adapter = challengeRecommendAdapter
            }
        }
    }

    override fun setupCollect() {
        repeatCallDefaultOnStarted {
            viewModel.event.collect { event ->
                when (event) {
                    HomeViewEvent.ClickCert -> {
                        showCertBottomSheet()
                    }
                    HomeViewEvent.ClickChallenge -> {
                        showChallengeTab()
                    }
                }
            }
        }

        repeatCallDefaultOnStarted {
            viewModel.state.collect { state ->
                when (state) {
                    HomeState.Init -> {
                        viewModel.getUserName()
                        viewModel.getRecommendedMountainData()
                    }
                }
            }
        }

        repeatCallDefaultOnStarted {
            viewModel.inProgressChallengeData.collect { result ->
                binding.cvEmptyChallenge.isVisible = result.isNullOrEmpty()
                binding.challengeList.isVisible = !result.isNullOrEmpty()
                onGoingChallengeListAdapter.submitList(result)
            }
        }

        repeatCallDefaultOnStarted {
            viewModel.recommendChallengeList.collect {
                challengeRecommendAdapter.submitList(it)
            }
        }
    }

    private fun showCertBottomSheet() {
        HomeCertifyBottomSheet.newInstance()
            .show(parentFragmentManager, HomeCertifyBottomSheet.TAG)
    }

    private fun showChallengeTab() {
        activityViewModel.setChallengeTab()
    }


    private val mountainRecommendAdapter by lazy {
        HomeMountainRecommendAdapter { mountainName ->
            mountainName?.let { goToMountainDetail(it) }
        }
    }


    private val onGoingChallengeListAdapter by lazy {
        OnGoingChallengeListAdapter { challengeData ->
            goToChallengeDetail(challengeData.id)
        }
    }

    private val challengeRecommendAdapter by lazy {
        HomeChallengeRecommendAdapter { challengeId ->
            goToChallengeDetail(challengeId)
        }
    }

    private fun goToMountainDetail(mountainName: String) {
        startActivity(Intent(requireContext(), MountainInfoDetailActivity::class.java).apply {
            putExtra(MountainInfoDetailActivity.MOUNTAIN_NAME, mountainName)
        })
    }


    private fun goToChallengeDetail(challengeId: Int) {
        startActivity(Intent(requireContext(), ChallengeDetailActivity::class.java).apply {
            putExtra(ChallengeDetailActivity.CHALLENGE_ID, challengeId)
        })
    }

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }
}