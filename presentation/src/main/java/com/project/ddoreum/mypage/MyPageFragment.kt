package com.project.ddoreum.mypage

import android.content.Intent
import androidx.fragment.app.viewModels
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import com.project.ddoreum.R
import com.project.ddoreum.common.hikingprogress.HikingProgressAdapter
import com.project.ddoreum.common.repeatCallDefaultOnStarted
import com.project.ddoreum.core.BaseFragment
import com.project.ddoreum.databinding.FragmentMyPageBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyPageFragment : BaseFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {

    override val viewModel: MyPageViewModel by viewModels()

    override fun initLayout() {
        bind {
            vm = viewModel
            lifecycleOwner = viewLifecycleOwner

            progressList.adapter = HikingProgressAdapter()
        }

    }

    override fun setupCollect() {
        repeatCallDefaultOnStarted {
            viewModel.state.collect { state ->
                when (state) {
                    MyPageState.Init -> {
                        viewModel.getUserName()
                    }
                }
            }
        }

        repeatCallDefaultOnStarted {
            viewModel.event.collect { event ->
                when (event) {
                    MyPageViewEvent.OpenSource -> {
                        startActivity(Intent(requireContext(), OssLicensesMenuActivity::class.java))
                    }
                }
            }
        }
    }

    companion object {
        fun newInstance() = MyPageFragment()
    }
}