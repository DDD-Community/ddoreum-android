package com.project.ddoreum

import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.commitNow
import com.project.ddoreum.challenge.ChallengeFragment
import com.project.ddoreum.core.BaseActivity
import com.project.ddoreum.databinding.ActivityMainBinding
import com.project.ddoreum.home.HomeFragment
import com.project.ddoreum.mountaininfo.MountainInfoFragment
import com.project.ddoreum.mypage.MyPageFragment
import com.project.ddoreum.record.RecordFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override val viewModel: MainViewModel by viewModels()

    override fun initLayout() {
        setUpNavigation()

        val currentFragment = supportFragmentManager.primaryNavigationFragment
        if (currentFragment == null) {
            changeFragment(HomeFragment.newInstance(), HomeFragment.toString())
        }
    }

    private fun setUpNavigation() = with(binding) {
        bottomNavView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> {
                    changeFragment(HomeFragment.newInstance(), HomeFragment.toString())
                }
                R.id.nav_record -> {
                    changeFragment(RecordFragment.newInstance(), RecordFragment.toString())
                }
                R.id.nav_mountain_info -> {
                    changeFragment(
                        MountainInfoFragment.newInstance(),
                        MountainInfoFragment.toString()
                    )
                }
                R.id.nav_challenge -> {
                    changeFragment(ChallengeFragment.newInstance(), ChallengeFragment.toString())
                }
                R.id.nav_my_page -> {
                    changeFragment(MyPageFragment.newInstance(), MyPageFragment.toString())
                }
            }
            return@setOnNavigationItemSelectedListener true
        }
    }

    private fun changeFragment(fragment: Fragment, tagFragmentName: String?) {
        supportFragmentManager.commitNow(true) {
            val currentFragment = supportFragmentManager.primaryNavigationFragment
            if (currentFragment != null) {
                hide(currentFragment)
            }

            var newFragment = supportFragmentManager.findFragmentByTag(tagFragmentName)
            if (newFragment == null) {
                newFragment = fragment
                add(R.id.container, newFragment, tagFragmentName)
            } else {
                show(newFragment)
            }

            setPrimaryNavigationFragment(newFragment)
            setReorderingAllowed(true)
        }
    }
}