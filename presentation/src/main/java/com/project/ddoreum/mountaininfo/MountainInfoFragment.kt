package com.project.ddoreum.mountaininfo

import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.commitNow
import androidx.fragment.app.viewModels
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.project.ddoreum.R
import com.project.ddoreum.core.BaseFragment
import com.project.ddoreum.databinding.FragmentMountainInfoBinding
import com.project.ddoreum.home.HomeFragment
import com.project.ddoreum.mountaininfo.list.MountainInfoListFragment
import com.project.ddoreum.mountaininfo.map.MountainInfoMapFragment
import com.project.ddoreum.mountaininfo.search.MountainInfoSearchFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MountainInfoFragment :
    BaseFragment<FragmentMountainInfoBinding>(R.layout.fragment_mountain_info) {

    override val viewModel: MountainInfoViewModel by viewModels()

    override fun initLayout() {
        bind {
            viewmodel = viewModel
        }
        initFragment()
        initGetData()
        ininCollect()
    }

    private fun ininCollect() {
    }

    private fun initGetData() {
        viewModel.getAllMountainInfo()
    }

    private fun initFragment() {
        val currentFragment = parentFragmentManager.primaryNavigationFragment
        if (currentFragment == null) {
            changeFragment(MountainInfoMapFragment.newInstance(), MountainInfoMapFragment.TAG)
        }
    }

    private fun changeFragment(fragment: Fragment, tagFragmentName: String?) {
        parentFragmentManager.commitNow(true) {
            val currentFragment = parentFragmentManager.primaryNavigationFragment
            if (currentFragment != null) {
                hide(currentFragment)
            }

            var newFragment = parentFragmentManager.findFragmentByTag(tagFragmentName)
            if (newFragment == null) {
                newFragment = fragment
                add(R.id.mountain_info_container, newFragment, tagFragmentName)
            } else {
                show(newFragment)
            }

            setPrimaryNavigationFragment(newFragment)
            setReorderingAllowed(true)
        }
    }

    private fun initDefaultFragment() {
        parentFragmentManager.commitNow(true) {
            add(MountainInfoMapFragment.newInstance(), MountainInfoMapFragment.TAG)
            add(MountainInfoListFragment.newInstance(), MountainInfoListFragment.TAG)
            add(MountainInfoSearchFragment.newInstance(), MountainInfoSearchFragment.TAG)
        }
    }

    companion object {
        fun newInstance() = MountainInfoFragment()
    }
}