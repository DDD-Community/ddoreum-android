package com.project.ddoreum.intro

import android.Manifest
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.gun0912.tedpermission.coroutine.TedPermission
import com.project.ddoreum.MainActivity
import com.project.ddoreum.R
import com.project.ddoreum.core.BaseActivity
import com.project.ddoreum.databinding.ActivitySplashBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {

    override val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initLayout()
    }

    override fun initLayout() {
        bind {
            vm = viewModel
            lifecycleOwner = this@SplashActivity

        }

        lifecycleScope.launch {
            viewModel.state.collect { state ->
                when (state) {
                    SplashState.Init -> {
                        viewModel.initSplash()
                    }
                    SplashState.Permission -> {
                        setupPermissionPopup()
                    }
                    SplashState.Finish -> {
                        startMainActivity()
                    }
                }
            }
        }
    }

    private fun startMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    private fun setupPermissionPopup() {
        // TODO : 권한 팝업 설정 필요

        lifecycleScope.launch {
            setupPermission()
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
            .setDeniedTitle("또오름을 이용하기 위해서 권한을 허용해 주세요")
            .setDeniedMessage("[설정] -> [어플리케이션] 에서 권한을 허용해 주세요")
            .setDeniedCloseButtonText("닫기")
            .check()

        if (permissionResult.isGranted) {
            viewModel.setPermissionCompleted()
        } else {
            finishAffinity()
        }
    }
}