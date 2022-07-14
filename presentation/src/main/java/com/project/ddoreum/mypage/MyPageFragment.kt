package com.project.ddoreum.mypage

import android.content.Intent
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import com.project.ddoreum.R
import com.project.ddoreum.common.hikingprogress.HikingProgressAdapter
import com.project.ddoreum.core.BaseFragment
import com.project.ddoreum.databinding.FragmentMyPageBinding
import com.project.ddoreum.di.MainDispatcher
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MyPageFragment : BaseFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {

    @Inject
    @MainDispatcher
    lateinit var mainDispatcher: CoroutineDispatcher

    override val viewModel: MyPageViewModel by viewModels()

    override fun initLayout() {
        bind {
            vm = viewModel
            lifecycleOwner = viewLifecycleOwner

            progressList.adapter = HikingProgressAdapter()
        }

    }

    override fun setupCollect() {
        lifecycleScope.launch(mainDispatcher) {
            viewModel.state.collect { state ->
                when (state) {
                    MyPageState.OpenSource -> {
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