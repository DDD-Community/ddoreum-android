package com.project.ddoreum.mountaininfo.search

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class SearchFilterAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity){

    override fun getItemCount(): Int = SEARCH_TAB_ITEM

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> {
                FavoriteListFragment.newInstance()
            }
            1 -> {
                AreaTypeFragment.newInstance()
            }
            else -> {
                RecentSearchFragment.newInstance()
            }
        }
    }

    companion object{
        const val SEARCH_TAB_ITEM = 3
    }
}