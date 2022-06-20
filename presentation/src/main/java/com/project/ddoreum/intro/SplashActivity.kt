package com.project.ddoreum.intro

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
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
                        setupPermission()
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

    private fun setupPermission() {
        // TODO : 권한 팝업 들어갈 자리
    }
}