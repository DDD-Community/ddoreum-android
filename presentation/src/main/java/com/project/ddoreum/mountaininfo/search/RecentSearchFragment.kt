package com.project.ddoreum.mountaininfo.search

import androidx.fragment.app.viewModels
import com.project.ddoreum.R
import com.project.ddoreum.core.BaseFragment
import com.project.ddoreum.databinding.FragmentRecentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecentSearchFragment : BaseFragment<FragmentRecentSearchBinding>(R.layout.fragment_recent_search) {

    companion object {
        @JvmStatic
        fun newInstance() = RecentSearchFragment()
    }

    override val viewModel: MountainInfoSearchViewModel by viewModels({ requireParentFragment() })

    override fun initLayout() {
    }
}