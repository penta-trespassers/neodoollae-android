package com.pentatrespassers.neodoollae.view.login.main

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.pentatrespassers.neodoollae.databinding.DialogAddFriendBinding
import com.pentatrespassers.neodoollae.databinding.DialogCheckFriendBinding
import com.pentatrespassers.neodoollae.databinding.FragmentFriendBinding
import com.pentatrespassers.neodoollae.view.login.main.friend.FriendListFragment
import com.pentatrespassers.neodoollae.view.login.main.friend.FriendPagerFragmentStateAdapter
import com.pentatrespassers.neodoollae.view.login.main.friend.FriendRequestFragment
import splitties.toast.toast

class FriendFragment private constructor() : Fragment() {

    private lateinit var bind: FragmentFriendBinding

    private var isFriendExist = false
    private var code = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentFriendBinding.inflate(inflater, container, false)
        with(bind) {
            val pagerAdapter = FriendPagerFragmentStateAdapter(requireActivity())
            // Fragment 2개 Add
            pagerAdapter.addFragment(FriendListFragment())
            pagerAdapter.addFragment(FriendRequestFragment())

            // Adapter
            viewPagerFriend.adapter = pagerAdapter

            viewPagerFriend.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    when (position) {
                        0 -> {
                            // 친구 목록창
                            friendSearchViewFriend.isVisible = true
                            filteringSwitchFriend.isVisible = true
                        }

                        1 -> {
                            // 친구 요청창
                            friendSearchViewFriend.isVisible = false
                            filteringSwitchFriend.isVisible = false
                        }

                    }
                }
            })

            // TabLayout attach
            TabLayoutMediator(friendTabLayoutFriend, viewPagerFriend) { tab, position ->
                when (position) {
                    0 -> {
                        tab.text = "친구 목록"
                    }
                    1 -> {
                        tab.text = "친구 요청"
                    }
                }
            }.attach()


            addFriendBtnFriend.setOnClickListener {
                showAddingDialog()

            }



            return root
        }
    }


    private fun showAddingDialog(){
        // Dialog만들기
        val dialogBind = DialogAddFriendBinding.inflate(layoutInflater)
        with(dialogBind) {
            val mBuilder = AlertDialog.Builder(context)
                .setView(root)
                .setCancelable(false).show()


            acceptButtonFriend.setOnClickListener {
                toast("code: ${codeEditTextFriend.text}")
                mBuilder.dismiss()
                // 만약 해당 코드를 가진 친구가 존재한다면
                showCheckDialog()

                // 해당 코드를 가진 친구가 존재하지 않는다면
                // showRejectDialog()
                // 혹은 그냥 간단 toast로 해당 코드 가진 사람이 존재하지 않는다고만 띄워도 될 듯.

            }
            cancelButtonFriend.setOnClickListener {
                mBuilder.dismiss()
            }
        }

    }

    private fun showCheckDialog() {
        val dialogBind = DialogCheckFriendBinding.inflate(layoutInflater)
        with(dialogBind) {
            val mBuilder = AlertDialog.Builder(context)
                .setView(root)
                .setCancelable(false).show()

            sendButtonCheckFriend.setOnClickListener {
                Toast.makeText(context, "친구 신청 완료", Toast.LENGTH_SHORT).show()
                mBuilder.dismiss()
            }

            cancelButtonCheckFriend.setOnClickListener {
                mBuilder.dismiss()
            }
        }
    }

    private fun showRejectDialog() {

    }

    companion object {
        fun newInstance() = FriendFragment()
    }


}