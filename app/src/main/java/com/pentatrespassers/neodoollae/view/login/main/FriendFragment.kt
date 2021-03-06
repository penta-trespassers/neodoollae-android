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
import splitties.resources.str

class FriendFragment constructor() : Fragment() {

    private lateinit var bind: FragmentFriendBinding


    private val friendListFragment = FriendListFragment.newInstance()
    private val friendRequestFragment = FriendRequestFragment.newInstance()

    var addFriendButton: ImageButton? = null

    // 하단 친구 추가 창
    private val addFriendBind by lazy { BtmSheetAddFriendBinding.inflate(layoutInflater) }
    private val addFriendDialog by lazy { BottomSheetDialog(requireContext()) }

    // 하단 친구 확인 창
    private val checkFriendBind by lazy { BtmSheetCheckFriendBinding.inflate(layoutInflater) }
    private val checkFriendDialog by lazy { BottomSheetDialog(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentFriendBinding.inflate(inflater, container, false)
        with(bind) {
            fragmentTransaction {
                add(R.id.friendFrame, friendListFragment)
                add(R.id.friendFrame, friendRequestFragment)
                hide(friendRequestFragment)
            }

            friendListConstraint.setOnClickListener {
                fragmentTransaction {
                    hide(friendRequestFragment)
                    show(friendListFragment)
                    friendListText.setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.blue_grey_800
                        )
                    )
                    friendListUnderlineConstraint.visibility = View.VISIBLE
                    friendRequestText.setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.blue_grey_200
                        )
                    )
                    friendRequestUnderlineConstraint.visibility = View.GONE
                }
            }
            friendRequestConstraint.setOnClickListener {
                fragmentTransaction {
                    hide(friendListFragment)
                    show(friendRequestFragment)
                    friendRequestText.setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.blue_grey_800
                        )
                    )
                    friendRequestUnderlineConstraint.visibility = View.VISIBLE
                    friendListText.setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.blue_grey_200
                        )
                    )
                    friendListUnderlineConstraint.visibility = View.GONE
                }
            }

            addFriendDialog.setContentView(addFriendBind.root)
            checkFriendDialog.setContentView(checkFriendBind.root)

            addFriendBind.apply {
                acceptButton.setOnClickListener {
                    acceptButton.isClickable = false
                    RetrofitClient.getUser(friendCodeEditText.text.toString()) { _, response ->
                        val user = response.body()
                        if (user == null) {
                            errorTextAddFriend.apply { text = str(R.string.invalid_friend_code) }.show()
                        } else {
                            checkFriendBind.friendNameTextCheckFriend.text = user.nickname
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
                RetrofitClient.sendFriendRequest(addFriendBind.friendCodeEditText.text.toString(),
                    { _, response ->
                        addFriendBind.errorTextAddFriend.apply { text = str(R.string.already_friend) }.show()
                        checkFriendDialog.dismiss()

                    }) { _, _ ->
                    addFriendDialog.dismiss()
                    checkFriendDialog.dismiss()
                }

            }

            return root
        }
    }

    fun addFriend(friendCode: String? = null) {
        addFriendDialog.show()
        friendCode?.let {
            addFriendBind.friendCodeEditText.setText(it)
            addFriendBind.acceptButton.performClick()
        }
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            friendListFragment.refreshFriendList()
            friendRequestFragment.refreshFriendRequest()
        }
    }

    fun setBadgeCount(count: String) {
        bind.friendRequestBadge.text = count
    }


    companion object {
        fun newInstance(addFriendButton: ImageButton) = FriendFragment().apply {
            this.addFriendButton = addFriendButton
        }
    }


}