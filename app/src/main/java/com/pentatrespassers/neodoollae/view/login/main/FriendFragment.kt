package com.pentatrespassers.neodoollae.view.login.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.databinding.BtmSheetAddFriendBinding
import com.pentatrespassers.neodoollae.databinding.BtmSheetCheckFriendBinding
import com.pentatrespassers.neodoollae.databinding.FragmentFriendBinding
import com.pentatrespassers.neodoollae.lib.Util.fragmentTransaction
import com.pentatrespassers.neodoollae.lib.Util.show
import com.pentatrespassers.neodoollae.network.RetrofitClient
import com.pentatrespassers.neodoollae.view.login.main.friend.FriendListFragment
import com.pentatrespassers.neodoollae.view.login.main.friend.FriendRequestFragment
import splitties.toast.toast

class FriendFragment private constructor() : Fragment() {

    private lateinit var bind: FragmentFriendBinding


    private val friendListFragment = FriendListFragment.newInstance()
    private val friendRequestFragment = FriendRequestFragment.newInstance()

    lateinit var addFriendButton: ImageButton

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

            // 하단 친구 추가 창
            val addFriendBind = BtmSheetAddFriendBinding.inflate(layoutInflater)
            val addFriendDialog = BottomSheetDialog(requireContext())
            addFriendDialog.setContentView(addFriendBind.root)

            // 하단 친구 확인 창
            val checkFriendBind = BtmSheetCheckFriendBinding.inflate(layoutInflater)
            val checkFriendDialog = BottomSheetDialog(requireContext())
            checkFriendDialog.setContentView(checkFriendBind.root)

            addFriendBind.apply {
                acceptButton.setOnClickListener {
                    acceptButton.isClickable = false
                    RetrofitClient.getUser(friendCodeEditText.text.toString()) { _, response ->
                        val user = response.body()
                        if (user == null) {
                            errorTextAddFriend.show()
                        } else {
                            checkFriendDialog.show()
                        }
                        acceptButton.isClickable = true
                    }
                }
            }

            checkFriendBind.cancelButtonCheckFriend.setOnClickListener {
                checkFriendDialog.dismiss()
            }
            checkFriendBind.sendButtonCheckFriend.setOnClickListener {
                addFriendDialog.dismiss()
                checkFriendDialog.dismiss()
                toast("친구 신청 완료!")
            }

            addFriendButton.setOnClickListener {
                addFriendDialog.show()
            }

            return root
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
        fun newInstance(addFriendButton: ImageButton) = FriendFragment().apply {
            this.addFriendButton = addFriendButton
        }
    }


}