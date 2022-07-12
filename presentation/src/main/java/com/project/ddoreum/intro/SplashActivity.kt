package com.project.ddoreum.intro

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.project.ddoreum.MainActivity
import com.project.ddoreum.R
import com.project.ddoreum.core.BaseActivity
import com.project.ddoreum.databinding.ActivitySplashBinding
import com.project.ddoreum.di.MainDispatcher
import com.project.ddoreum.intro.permission.SplashPermissionDialog
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {

    override val viewModel: SplashViewModel by viewModels()

    @Inject
    @MainDispatcher
    lateinit var mainDispatcher: CoroutineDispatcher

    private val loginLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            val task: Task<GoogleSignInAccount> =
                GoogleSignIn.getSignedInAccountFromIntent(result.data)

            viewModel.handleUserInformation(task)
        }

    lateinit var mGoogleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
    }

    override fun initLayout() {
        bind {
            vm = viewModel
            lifecycleOwner = this@SplashActivity
        }

        lifecycleScope.launch(mainDispatcher) {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
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
                        SplashState.SuccessPermission -> {
                            viewModel.getUserInfoLoggedIn()
                        }
                        SplashState.FailLogin -> {
                            Toast.makeText(
                                this@SplashActivity,
                                getString(R.string.login_failed),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        is SplashState.Finish -> {
                            Toast.makeText(
                                this@SplashActivity,
                                getString(R.string.login_success, state.name),
                                Toast.LENGTH_SHORT
                            ).show()
                            startMainActivity()
                        }
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
        listOf(
            Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ).forEach {
            if (ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED) {
                SplashPermissionDialog
                    .newInstance()
                    .show(supportFragmentManager, SplashPermissionDialog.TAG)
                return
            }
        }
        viewModel.setPermissionCompleted()
    }

    private fun setupLogin() {
        loginLauncher.launch(mGoogleSignInClient.signInIntent)
    }
}