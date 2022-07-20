package com.project.ddoreum.home

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearSnapHelper
import com.project.ddoreum.R
import com.project.ddoreum.challenge.OnGoingChallengeListAdapter
import com.project.ddoreum.common.hikingprogress.HikingProgressAdapter
import com.project.ddoreum.common.repeatCallDefaultOnStarted
import com.project.ddoreum.core.BaseFragment
import com.project.ddoreum.databinding.FragmentHomeBinding
import com.project.ddoreum.di.MainDispatcher
import com.project.ddoreum.home.certify.HomeCertifyBottomSheet
import com.project.ddoreum.home.newchallenge.HomeChallengeRecommendAdapter
import com.project.ddoreum.home.recommend.HomeMountainRecommendAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    @Inject
    @MainDispatcher
    lateinit var mainDispatcher: CoroutineDispatcher

    override val viewModel: HomeViewModel by viewModels()

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
                // TODO : 산 추천 리스트 받으면 재진행
                adapter = HomeMountainRecommendAdapter()
            }

            challengeRecommendList.apply {
                LinearSnapHelper().attachToRecyclerView(this)
                adapter = HomeChallengeRecommendAdapter()
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
                }
            }
        }

        repeatCallDefaultOnStarted {
            viewModel.state.collect { state ->
                when (state) {
                    HomeState.Init -> {
                        // TODO : Home Fragment Init시 가져올 데이터 정리
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
    }

    private fun showCertBottomSheet() {
        HomeCertifyBottomSheet.newInstance()
            .show(parentFragmentManager, HomeCertifyBottomSheet.TAG)
    }

    private val onGoingChallengeListAdapter by lazy {
        OnGoingChallengeListAdapter {
        }
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}