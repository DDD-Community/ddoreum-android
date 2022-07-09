package com.project.ddoreum.mountaininfo.search.favorite

import android.content.Intent
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.ddoreum.R
import com.project.ddoreum.common.repeatCallDefaultOnCreated
import com.project.ddoreum.core.BaseFragment
import com.project.ddoreum.databinding.FragmentFavoriteListBinding
import com.project.ddoreum.mountaininfo.detail.MountainInfoDetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteListFragment : BaseFragment<FragmentFavoriteListBinding>(R.layout.fragment_favorite_list) {

    companion object {
        @JvmStatic
        fun newInstance() = FavoriteListFragment()
    }

    override val viewModel: FavoriteListViewModel by viewModels()

    override fun initLayout() {
        viewModel.getAllFavoriteMountainList()
        initRcvView()
        observeFlow()
    }

    private fun initRcvView() = with(binding) {
        rcvFavoriteList.adapter = mountainFavoriteListAdapter
        rcvFavoriteList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    private fun observeFlow() = viewLifecycleOwner.repeatCallDefaultOnCreated {
        viewModel.favoriteList.collect {
            binding.tvEmptyFavorite.isVisible = it.isNullOrEmpty()
            mountainFavoriteListAdapter.submitList(it)
        }
    }

    private val mountainFavoriteListAdapter by lazy {
        MountainFavoriteListAdapter {
            val intent = Intent(activity, MountainInfoDetailActivity::class.java).apply {
                putExtra(MountainInfoDetailActivity.MOUNTAIN_NAME, it.name)
            }
            startActivity(intent)
        }
    }
}