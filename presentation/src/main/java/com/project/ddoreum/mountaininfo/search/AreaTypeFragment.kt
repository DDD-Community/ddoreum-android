package com.project.ddoreum.mountaininfo.search

import androidx.fragment.app.viewModels
import com.project.ddoreum.R
import com.project.ddoreum.core.BaseFragment
import com.project.ddoreum.databinding.FragmentAreaTypeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AreaTypeFragment : BaseFragment<FragmentAreaTypeBinding>(R.layout.fragment_area_type) {

    companion object {
        @JvmStatic
        fun newInstance() = AreaTypeFragment()
    }

    override val viewModel: MountainInfoSearchViewModel by viewModels({ requireParentFragment() })

    override fun initLayout() {
    }
}