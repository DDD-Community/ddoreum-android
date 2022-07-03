package com.project.ddoreum.mountaininfo

import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.project.ddoreum.R
import com.project.ddoreum.common.hideKeyboard
import com.project.ddoreum.common.repeatCallDefaultOnCreated
import com.project.ddoreum.core.BaseFragment
import com.project.ddoreum.databinding.FragmentMountainInfoBinding
import com.project.ddoreum.mountaininfo.list.MountainInfoListFragment
import com.project.ddoreum.mountaininfo.map.MountainInfoMapFragment
import com.project.ddoreum.mountaininfo.search.MountainInfoSearchFragment
import com.project.ddoreum.mountaininfo.state.MainViewType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MountainInfoFragment :
    BaseFragment<FragmentMountainInfoBinding>(R.layout.fragment_mountain_info) {

    override val viewModel: MountainInfoViewModel by viewModels()

    override fun initLayout() {
        bind {
            viewmodel = viewModel
            searchView.isIconified = false
        }
        initFragment()
        initSearchView()
        observeFlow()
        viewModel.getAllMountainInfo()
    }

    private fun observeFlow() = viewLifecycleOwner.repeatCallDefaultOnCreated {
        viewModel.mainViewType.collect { mainViewType ->
            setMainViewType(mainViewType)
        }
    }

    private fun setMainViewType(mainViewType: MainViewType) {
        when (mainViewType) {
            MainViewType.MapType -> {
                changeFragment(mountainInfoViewFragmentList[0])
                updateHeaderIcon(MainViewType.MapType)
                clearSearchView()
            }
            MainViewType.ListType -> {
                changeFragment(mountainInfoViewFragmentList[1])
                updateHeaderIcon(MainViewType.ListType)
                clearSearchView()
            }
            MainViewType.SearchType -> {
                changeFragment(mountainInfoViewFragmentList[2])
            }
        }
    }

    private fun clearSearchView() = with(binding) {
        searchView.apply {
            clearFocus()
            hideKeyboard()
        }
    }

    private fun initFragment() {
        childFragmentManager.beginTransaction().apply {
            add(R.id.mountain_info_container, mountainInfoViewFragmentList[0])
            add(R.id.mountain_info_container, mountainInfoViewFragmentList[1])
            add(R.id.mountain_info_container, mountainInfoViewFragmentList[2])
        }.commitNow()

        changeFragment(mountainInfoViewFragmentList[1])
    }

    private fun initSearchView() = with(binding.searchView) {
        isFocusable = false
        isIconified = false
        clearFocus()
        setOnQueryTextFocusChangeListener { view, b ->
            if (b) {
                viewModel.switchMainViewTypeToSearch()
            }
        }
    }

    private fun changeFragment(recentFragment: Fragment){
        childFragmentManager.beginTransaction().apply {
            mountainInfoViewFragmentList
                .filter { baseFragment ->
                    baseFragment != recentFragment
                }.map { fragment ->
                    hide(fragment)
                }
            show(recentFragment)
        }.commitNow()
    }

    private fun updateHeaderIcon(type: MainViewType) = with(binding) {
        when (type) {
            MainViewType.MapType -> {
                ivSwitchMapList.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_list))
            }
            MainViewType.ListType -> {
                ivSwitchMapList.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_map))
            }
        }
    }

    private val mountainInfoViewFragmentList by lazy {
        listOf(
            MountainInfoMapFragment.newInstance(),
            MountainInfoListFragment.newInstance(),
            MountainInfoSearchFragment.newInstance()
        )
    }

    companion object {
        fun newInstance() = MountainInfoFragment()
    }
}