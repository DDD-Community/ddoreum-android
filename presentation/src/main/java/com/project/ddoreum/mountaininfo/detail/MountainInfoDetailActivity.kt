package com.project.ddoreum.mountaininfo.detail

import androidx.activity.viewModels
import com.project.ddoreum.R
import com.project.ddoreum.core.BaseActivity
import com.project.ddoreum.databinding.ActivityMountainInfoDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MountainInfoDetailActivity : BaseActivity<ActivityMountainInfoDetailBinding>(R.layout.activity_mountain_info_detail) {

    override val viewModel: MountainInfoDetailViewModel by viewModels()

    private val mountainName by lazy {
        intent.getStringExtra(MOUNTAIN_NAME)
    }

    override fun initLayout() {
        bind {
            viewmodel = viewModel
            lifecycleOwner = this@MountainInfoDetailActivity
        }
        initRequestData()
    }

    private fun initRequestData() {
        mountainName?.let { name ->
            viewModel.getMountainDetailInfo(name)
        }
    }

    companion object {
        const val MOUNTAIN_NAME = "mountainName"
        fun newInstance(mountainName: String): MountainInfoDetailActivity {
            return MountainInfoDetailActivity().apply {
                intent.putExtra(MOUNTAIN_NAME, mountainName)
            }
        }
    }

}