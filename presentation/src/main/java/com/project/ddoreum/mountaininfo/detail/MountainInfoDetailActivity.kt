package com.project.ddoreum.mountaininfo.detail

import android.widget.Toast
import androidx.activity.viewModels
import com.project.ddoreum.R
import com.project.ddoreum.common.repeatCallDefaultOnStarted
import com.project.ddoreum.core.BaseActivity
import com.project.ddoreum.databinding.ActivityMountainInfoDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MountainInfoDetailActivity :
    BaseActivity<ActivityMountainInfoDetailBinding>(R.layout.activity_mountain_info_detail) {

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

        repeatCallDefaultOnStarted {
            viewModel.registerFavoriteMountain.collect {
                Toast.makeText(
                    this@MountainInfoDetailActivity,
                    if (it) "즐겨찾는 산으로 등록했어요!" else "즐겨찾는 산에서 제거했어요!", Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun initRequestData() {
        mountainName?.let { name ->
            viewModel.getMountainDetailInfo(name)
        }
    }

    companion object {
        const val MOUNTAIN_NAME = "mountainName"
    }
}