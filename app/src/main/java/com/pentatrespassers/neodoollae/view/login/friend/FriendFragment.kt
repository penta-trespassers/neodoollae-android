package com.pentatrespassers.neodoollae.view.login.friend

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.pentatrespassers.neodoollae.databinding.FragmentFriendBinding

class FriendFragment private constructor() : Fragment() {

    private lateinit var bind: FragmentFriendBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentFriendBinding.inflate(inflater, container, false)
        with(bind) {
            return root
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val pagerAdapter = FriendPagerFragmentStateAdapter(requireActivity())
        // Fragment 2개 Add
        pagerAdapter.addFragment(FriendListFragment())
        pagerAdapter.addFragment(FriendRequestFragment())

        // Adapter
        bind.viewPagerFriend.adapter = pagerAdapter

        bind.viewPagerFriend.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                Log.e("ViewPagerFragment", "Page ${position+1}")
                when (position) {
                    0 -> {
                        // 친구 목록창
                        bind.friendSearchViewFriend.isVisible = true
                        bind.filteringSwitchFriend.isVisible = true
                    }

                    1 -> {
                        // 친구 요청창
                        bind.friendSearchViewFriend.isVisible = false
                        bind.filteringSwitchFriend.isVisible = false
                    }

                }
            }
        })

        // TabLayout attach
        TabLayoutMediator(bind.friendTabLayoutFriend, bind.viewPagerFriend) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "친구 목록"
                }
                1 -> {
                    tab.text = "친구 요청"
                }
            }
        }.attach()
    }

    companion object {
        fun newInstance() = FriendFragment()
    }
}