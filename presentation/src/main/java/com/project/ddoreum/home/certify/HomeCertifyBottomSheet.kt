package com.project.ddoreum.home.certify

import androidx.fragment.app.viewModels
import com.project.ddoreum.R
import com.project.ddoreum.core.BaseBottomSheetFragment
import com.project.ddoreum.databinding.BottomsheetHomeCertifyBinding
import com.project.ddoreum.di.MainDispatcher
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher

@AndroidEntryPoint
class HomeCertifyBottomSheet :
    BaseBottomSheetFragment<BottomsheetHomeCertifyBinding>(R.layout.bottomsheet_home_certify) {

    override fun getTheme(): Int = R.style.DdoreumBottomSheetDialog

    @Inject
    @MainDispatcher
    lateinit var mainDispatcher: CoroutineDispatcher

    override val viewModel: HomeCertifyViewModel by viewModels()

    override fun initLayout() {
        bind {
            vm = viewModel
            lifecycleOwner = viewLifecycleOwner

        }

    }

    companion object {
        const val TAG = "HomeCertifyBottomSheet"

        fun newInstance(): HomeCertifyBottomSheet {
            return HomeCertifyBottomSheet()
        }
    }
}