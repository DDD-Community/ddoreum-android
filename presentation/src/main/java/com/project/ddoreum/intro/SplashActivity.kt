package com.project.ddoreum.intro

import android.content.Intent
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.project.ddoreum.MainActivity
import com.project.ddoreum.R
import com.project.ddoreum.core.BaseActivity
import com.project.ddoreum.databinding.ActivitySplashBinding
import com.project.ddoreum.intro.permission.SplashPermissionDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {

    override val viewModel: SplashViewModel by viewModels()

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
                    SplashState.Login -> {
                        setupLogin()
                    }
                    SplashState.RejectPermission -> {
                        finishAffinity()
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
        finish()
    }

    private fun setupPermissionPopup() {
        SplashPermissionDialog
            .newInstance()
            .show(supportFragmentManager, SplashPermissionDialog.TAG)
    }

    private fun setupLogin() {
        // TODO : 로그인 뷰 확인 이후 로직 필요
    }
}