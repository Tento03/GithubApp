package com.example.tes.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.tes.api.User

class ViewPagerAdapter(fragmentActivity: FragmentActivity, private val user: User) :
    FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FragmentFollowers.newInstance(user)
            1 -> FragmentFollowings.newInstance(user)
            else -> Fragment()
        }
    }

    override fun getItemCount(): Int {
        return 2
    }
}

