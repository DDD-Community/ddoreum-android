package com.project.ddoreum.intro.permission

import android.Manifest
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.gun0912.tedpermission.coroutine.TedPermission
import com.project.ddoreum.R
import com.project.ddoreum.core.BaseDialogFragment
import com.project.ddoreum.databinding.DialogSplashPermissionBinding
import com.project.ddoreum.intro.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashPermissionDialog :
    BaseDialogFragment<DialogSplashPermissionBinding>(R.layout.dialog_splash_permission) {

    override fun getTheme(): Int = R.style.FullScreenDialog

    private val activityViewModel: SplashViewModel by activityViewModels()

    override val viewModel: SplashPermissionViewModel by viewModels()

    override fun initLayout() {
        bind {
            vm = viewModel
            lifecycleOwner = viewLifecycleOwner

            list.adapter = SplashPermissionAdapter()
        }

        isCancelable = false

        lifecycleScope.launch {
            viewModel.state.collect { state ->
                when (state) {
                    SplashPermissionState.Init -> {
                        // Do Nothing
                    }
                    SplashPermissionState.Permission -> {
                        setupPermission()
                    }
                    SplashPermissionState.RejectPermission -> {
                        activityViewModel.setPermissionRejected()
                    }
                    SplashPermissionState.Finish -> {
                        activityViewModel.setPermissionCompleted()
                        dismiss()
                    }
                }
            }
        }
    }

    private suspend fun setupPermission() {
        val permissionResult = TedPermission.create()
            .setPermissions(
                Manifest.permission.CAMERA,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
            .setDeniedTitle("이용에 대한 액세스 권한이 없어요")
            .setDeniedMessage("앱 설정으로 가서 액세스 권한을 수정 할 수 있어요.\n이동하시겠어요?")
            .setDeniedCloseButtonText("닫기")
            .check()

        if (permissionResult.isGranted) {
            viewModel.finishCheckPermission()
        } else {
            viewModel.rejectCheckPermission()
        }
    }

    companion object {
        const val TAG = "SplashPermissionDialog"

        fun newInstance(): SplashPermissionDialog {
            return SplashPermissionDialog()
        }
    }
}
