package com.project.ddoreum.mountaininfo.search

import androidx.fragment.app.viewModels
import com.project.ddoreum.R
import com.project.ddoreum.core.BaseFragment
import com.project.ddoreum.databinding.FragmentFavoriteListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteListFragment : BaseFragment<FragmentFavoriteListBinding>(R.layout.fragment_favorite_list) {

    companion object {
        @JvmStatic
        fun newInstance() = FavoriteListFragment()
    }

    override val viewModel: MountainInfoSearchViewModel by viewModels({ requireParentFragment() })

    override fun initLayout() {
    }
}