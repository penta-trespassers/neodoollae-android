package com.pentatrespassers.neodoollae.view.login.main

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.databinding.DialogAddFriendBinding
import com.pentatrespassers.neodoollae.databinding.DialogCheckFriendBinding
import com.pentatrespassers.neodoollae.databinding.FragmentFriendBinding
import com.pentatrespassers.neodoollae.lib.Util.fragmentTransaction
import com.pentatrespassers.neodoollae.view.login.main.friend.FriendListFragment
import com.pentatrespassers.neodoollae.view.login.main.friend.FriendRequestFragment
import splitties.toast.toast

class FriendFragment private constructor() : Fragment() {

    private lateinit var bind: FragmentFriendBinding


    private val friendListFragment = FriendListFragment.newInstance()
    private val friendRequestFragment = FriendRequestFragment.newInstance()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentFriendBinding.inflate(inflater, container, false)
        with(bind) {
            fragmentTransaction {
                add(R.id.friendFrame,friendListFragment)
                add(R.id.friendFrame,friendRequestFragment)
                hide(friendRequestFragment)
            }
            friendListConstraint.setOnClickListener {
                fragmentTransaction {
                    hide(friendRequestFragment)
                    show(friendListFragment)
                    friendListText.setTextColor(ContextCompat.getColor(requireContext(), R.color.trespassBlue_900))
                    friendListUnderlineConstraint.visibility = View.VISIBLE
                    friendRequestText.setTextColor(ContextCompat.getColor(requireContext(), R.color.trespassGray_900))
                    friendRequestUnderlineConstraint.visibility = View.GONE
                }
            }
            friendRequestConstraint.setOnClickListener {
                fragmentTransaction {
                    hide(friendListFragment)
                    show(friendRequestFragment)
                    friendRequestText.setTextColor(ContextCompat.getColor(requireContext(), R.color.trespassBlue_900))
                    friendRequestUnderlineConstraint.visibility = View.VISIBLE
                    friendListText.setTextColor(ContextCompat.getColor(requireContext(), R.color.trespassGray_900))
                    friendListUnderlineConstraint.visibility = View.GONE
                }
            }


            addFriendButton.setOnClickListener {
                showAddingDialog()

            }

            return root
        }
    }


    private fun showAddingDialog() {
        // Dialog만들기
        val dialogBind = DialogAddFriendBinding.inflate(layoutInflater)
        with(dialogBind) {
            val mBuilder = AlertDialog.Builder(context)
                .setView(root)
                .setCancelable(false).show()


            acceptButton.setOnClickListener {
                toast("code: ${codeEditTextFriend.text}")
                mBuilder.dismiss()
                // 만약 해당 코드를 가진 친구가 존재한다면
                showCheckDialog()

                // 해당 코드를 가진 친구가 존재하지 않는다면
                // showRejectDialog()
                // 혹은 그냥 간단 toast로 해당 코드 가진 사람이 존재하지 않는다고만 띄워도 될 듯.

            }
            cancelButton.setOnClickListener {
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
                toast("친구 신청 완료")
                mBuilder.dismiss()
            }

            cancelButtonCheckFriend.setOnClickListener {
                mBuilder.dismiss()
            }
        }
    }


    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (hidden) {
            friendListFragment.refreshFriendList()
            friendRequestFragment.refreshFriendRequest()
        }
    }



    companion object {
        fun newInstance() = FriendFragment()
    }


}