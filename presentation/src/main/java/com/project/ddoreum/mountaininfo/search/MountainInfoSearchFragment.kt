package com.project.ddoreum.mountaininfo.search

import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.project.ddoreum.R
import com.project.ddoreum.core.BaseFragment
import com.project.ddoreum.databinding.FragmentMountainInfoSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MountainInfoSearchFragment : BaseFragment<FragmentMountainInfoSearchBinding>(R.layout.fragment_mountain_info_search) {

    companion object {
        val TAG = this::class.java.toString()
        fun newInstance() = MountainInfoSearchFragment()
    }

    override val viewModel: MountainInfoSearchViewModel by viewModels()

    override fun initLayout() {
        searchFilterAdapter = SearchFilterAdapter(requireActivity())
        initTab()
    }

    private fun initTab(){
        binding.vpSearchFilter.apply {
            adapter = searchFilterAdapter
            currentItem = 0
        }
        TabLayoutMediator(binding.tbLayoutSearchFilter, binding.vpSearchFilter){ tab: TabLayout.Tab, position: Int ->
            tab.customView = setItem(position)
        }.attach()
    }

    private fun setItem(position: Int) : View {
        val tabView = LayoutInflater.from(requireContext()).inflate(R.layout.item_tab, null)
        val tv_tab_item = tabView.findViewById<TextView>(R.id.tv_tab_item)
        tabView.apply {
            tv_tab_item.apply {
                textSize = 14.0f
                setBackgroundColor(resources.getColor(R.color.white))
            }
        }
        return when (position) {
            0 -> {
                tv_tab_item.text = "즐겨찾기"
                tabView
            }
            1 -> {
                tv_tab_item.text = "지역별"
                tabView
            }
            else -> {
                tv_tab_item.text = "최근 검색"
                tabView
            }
        }
    }

    lateinit var searchFilterAdapter : SearchFilterAdapter
}