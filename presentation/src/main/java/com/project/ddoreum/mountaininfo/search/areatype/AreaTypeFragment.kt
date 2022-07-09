package com.project.ddoreum.mountaininfo.search.areatype

import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.ddoreum.R
import com.project.ddoreum.common.repeatCallDefaultOnCreated
import com.project.ddoreum.core.BaseFragment
import com.project.ddoreum.databinding.FragmentAreaTypeBinding
import com.project.ddoreum.mountaininfo.MountainInfoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.filterNotNull

@AndroidEntryPoint
class AreaTypeFragment : BaseFragment<FragmentAreaTypeBinding>(R.layout.fragment_area_type) {

    companion object {
        @JvmStatic
        fun newInstance() = AreaTypeFragment()
    }

    override val viewModel: MountainInfoViewModel by activityViewModels()

    override fun initLayout() {
        Log.d("ViewModel 2 HashCode :: ", "${viewModel.hashCode()}")
        initView()
        initRcvView()
        collectFlow()
        collectDetailRegionFlow()
    }

    private fun initView() {
        binding.btnApplyAreaType.setOnClickListener {
            viewModel.updateAreaTypeSearchFlag(true)
        }
    }

    private fun initRcvView() = with(binding) {
        rcvRegion.adapter = areaTypeRegionListAdapter
        rcvRegion.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        areaTypeRegionListAdapter.submitList(AreaTypeListAdapter.areaList)
        viewModel.updateSearchRegion("서울특별시")

        rcvRegionDetail.adapter = areaTypeRegionDetailListAdapter
        rcvRegionDetail.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        val initialList = AreaTypeRegionDetailListAdapter.areaList["서울특별시"]?.split(",")
        areaTypeRegionDetailListAdapter.submitList(initialList)
    }

    private fun collectFlow() = repeatCallDefaultOnCreated {
        viewModel.searchRegion.filterNotNull().collect {
            val list = AreaTypeRegionDetailListAdapter.areaList[it]?.split(",")
            areaTypeRegionDetailListAdapter.submitList(list)
            areaTypeRegionListAdapter.notifyDataSetChanged()
        }
    }

    private fun collectDetailRegionFlow() = repeatCallDefaultOnCreated {
        viewModel.searchRegionDetail.collect {
            areaTypeRegionDetailListAdapter.notifyDataSetChanged()
        }
    }

    private val areaTypeRegionListAdapter by lazy {
        AreaTypeListAdapter {
            viewModel.updateSearchRegion(it)
        }
    }

    private val areaTypeRegionDetailListAdapter by lazy {
        AreaTypeRegionDetailListAdapter {
            viewModel.updateSearchRegionDetail(it)
        }
    }
}