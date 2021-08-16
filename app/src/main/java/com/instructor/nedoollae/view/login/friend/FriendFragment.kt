package com.instructor.nedoollae.view.login.friend

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.instructor.nedoollae.R
import com.instructor.nedoollae.databinding.FragmentFriendBinding

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

        val tabLayout = bind.friendTabLayoutFriend
//        bind.friendListTabItemFriend.text = "친구 목록"
//        bind.friendRequestTabItemFriend.text = "친구 요청"


        tabLayout.addTab(tabLayout.newTab().setText("친구 목록"))
        tabLayout.addTab(tabLayout.newTab().setText("친구 요청"))

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val pagerAdapter = FriendPagerFragmentStateAdapter(requireActivity())
        // Fragment 2개 Add
        pagerAdapter.addFragment(FriendListFragment())
        pagerAdapter.addFragment(FriendRequestFragment())

        // Adapter
        bind.viewPager.adapter = pagerAdapter

        bind.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                Log.e("ViewPagerFragment", "Page ${position+1}")
                when (position) {
                    0 -> {
                        // 친구 목록창
                        bind.friendSearchViewFriend.isVisible = true
                        bind.filteringCheckBoxFriendList.isVisible = true
                        bind.filteringSwitchFriendList.isVisible = true
                        bind.filteringTextViewFriendList.isVisible = true
                    }

                    1 -> {
                        // 친구 요청창
                        bind.friendSearchViewFriend.isVisible = false
                        bind.filteringCheckBoxFriendList.isVisible = false
                        bind.filteringSwitchFriendList.isVisible = false
                        bind.filteringTextViewFriendList.isVisible = false
                    }

                }
            }
        })

        // TabLayout attach
        TabLayoutMediator(bind.friendTabLayoutFriend, bind.viewPager) { tab, position ->
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