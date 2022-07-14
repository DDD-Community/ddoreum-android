package com.project.ddoreum.home

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearSnapHelper
import com.project.ddoreum.R
import com.project.ddoreum.common.hikingprogress.HikingProgressAdapter
import com.project.ddoreum.core.BaseFragment
import com.project.ddoreum.databinding.FragmentHomeBinding
import com.project.ddoreum.di.MainDispatcher
import com.project.ddoreum.home.challenge.HomeChallengeAdapter
import com.project.ddoreum.home.newchallenge.HomeChallengeRecommendAdapter
import com.project.ddoreum.home.recommend.HomeMountainRecommendAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

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
                adapter = HomeChallengeAdapter()
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
        lifecycleScope.launch(mainDispatcher) {
            viewModel.state.collect { state ->
                when (state) {
                    HomeState.CLick -> {
                        Toast.makeText(requireContext(), "dsfa", Toast.LENGTH_SHORT).show()
                    }

                }
            }
        }
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}