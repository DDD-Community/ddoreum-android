package com.project.ddoreum.mountaininfo.search.recentsearch

import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.project.ddoreum.R
import com.project.ddoreum.common.repeatCallDefaultOnCreated
import com.project.ddoreum.core.BaseFragment
import com.project.ddoreum.databinding.FragmentRecentSearchBinding
import com.project.ddoreum.mountaininfo.MountainInfoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecentSearchFragment : BaseFragment<FragmentRecentSearchBinding>(R.layout.fragment_recent_search) {

    companion object {
        @JvmStatic
        fun newInstance() = RecentSearchFragment()
    }

    override val viewModel: RecentSearchViewModel by viewModels()
    private val mainInfoViewModel: MountainInfoViewModel by activityViewModels()

    override fun initLayout() {
        bind {
            rcvRecentSearch.adapter = recentSearchKeywordAdapter
        }
        initView()
        initRequest()
        collectFlow()
    }

    private fun initView() {
        binding.tvClearAllRecentSearchKeyword.setOnClickListener {
            viewModel.deleteAllRecentKeyword()
        }
    }

    private fun initRequest() {
        viewModel.getAllRecentKeywordList()
    }

    private fun collectFlow() = repeatCallDefaultOnCreated {
        viewModel.allRecentKeywordList.collect {
            binding.tvEmptyRecentList.isVisible = it.isEmpty()
            recentSearchKeywordAdapter.submitList(it)
        }
    }

    private val recentSearchKeywordAdapter: RecentSearchKeywordListAdapter by lazy {
        RecentSearchKeywordListAdapter (
            onClick =  {
                mainInfoViewModel.updateSearchKeyword(it)
            },
            onRemoveClick = {
                viewModel.deleteRecentKeyword(it)
            }
        )
    }
}