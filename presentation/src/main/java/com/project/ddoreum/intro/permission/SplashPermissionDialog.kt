package com.project.ddoreum.intro.permission

import android.Manifest
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.gun0912.tedpermission.coroutine.TedPermission
import com.project.ddoreum.R
import com.project.ddoreum.core.BaseDialogFragment
import com.project.ddoreum.databinding.DialogSplashPermissionBinding
import com.project.ddoreum.di.MainDispatcher
import com.project.ddoreum.intro.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashPermissionDialog :
    BaseDialogFragment<DialogSplashPermissionBinding>(R.layout.dialog_splash_permission) {

    override fun getTheme(): Int = R.style.FullScreenDialog

    private val activityViewModel: SplashViewModel by activityViewModels()

    override val viewModel: SplashPermissionViewModel by viewModels()

    @Inject
    @MainDispatcher
    lateinit var mainDispatcher: CoroutineDispatcher


    override fun initLayout() {
        bind {
            vm = viewModel
            lifecycleOwner = viewLifecycleOwner

            list.adapter = SplashPermissionAdapter()
        }

        isCancelable = false

        lifecycleScope.launch(mainDispatcher) {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
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
    }

    private suspend fun setupPermission() {
        val permissionResult = TedPermission.create()
            .setPermissions(
                Manifest.permission.CAMERA,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
            .setDeniedTitle(R.string.permission_noti_popup_title)
            .setDeniedMessage(R.string.permission_noti_popup_description)
            .setGotoSettingButtonText(R.string.permission_noti_popup_setting)
            .setDeniedCloseButtonText(R.string.permission_noti_popup_close)
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
