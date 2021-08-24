package com.pentatrespassers.neodoollae.view.login.main

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.databinding.FragmentFriendBinding
import com.pentatrespassers.neodoollae.view.login.main.friend.FriendListFragment
import com.pentatrespassers.neodoollae.view.login.main.friend.FriendPagerFragmentStateAdapter
import com.pentatrespassers.neodoollae.view.login.main.friend.FriendRequestFragment

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
                    Log.e("ViewPagerFragment", "Page ${position+1}")
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
        val dlg = LayoutInflater.from(context).inflate(R.layout.dialog_add_friend, null)
        val mBuilder = AlertDialog.Builder(context)
            .setView(dlg)
            .setCancelable(false)

        val bldr = mBuilder.show()

        val btnOK = dlg.findViewById<Button>(R.id.acceptButtonFriend)
        btnOK.setOnClickListener {
            var codeET = dlg.findViewById<EditText>(R.id.codeEditTextFriend).text
            code = codeET.toString()
            Toast.makeText(context, "code: $code", Toast.LENGTH_SHORT).show()
            bldr.dismiss()

            // 만약 해당 코드를 가진 친구가 존재한다면
            showCheckDialog()

            // 해당 코드를 가진 친구가 존재하지 않는다면
            // showRejectDialog()
            // 혹은 그냥 간단 toast로 해당 코드 가진 사람이 존재하지 않는다고만 띄워도 될 듯.

        }

        val btnNO = dlg.findViewById<Button>(R.id.cancelButtonFriend)
        btnNO.setOnClickListener {
            bldr.dismiss()
        }
    }

    private fun showCheckDialog() {
        val dlg = LayoutInflater.from(context).inflate(R.layout.dialog_check_friend, null)
        val mBuilder = AlertDialog.Builder(context)
            .setView(dlg)
            .setCancelable(false)

        val bldr = mBuilder.show()

        val btnSend = dlg.findViewById<Button>(R.id.sendButtonCheckFriend)
        val btnNO = dlg.findViewById<Button>(R.id.cancelButtonCheckFriend)

        btnSend.setOnClickListener {
            Toast.makeText(context, "친구 신청 완료", Toast.LENGTH_SHORT).show()
            bldr.dismiss()
        }

        btnNO.setOnClickListener {
            bldr.dismiss()
        }
    }

    private fun showRejectDialog() {

    }

    companion object {
        fun newInstance() = FriendFragment()
    }


}