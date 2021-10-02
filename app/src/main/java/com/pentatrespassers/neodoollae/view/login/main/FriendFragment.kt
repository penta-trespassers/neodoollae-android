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
import com.pentatrespassers.neodoollae.dto.User
import com.pentatrespassers.neodoollae.lib.Util.fragmentTransaction
import com.pentatrespassers.neodoollae.network.RetrofitClient
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
                val dialogBind = DialogAddFriendBinding.inflate(layoutInflater)
                with(dialogBind) {
                    val mBuilder = AlertDialog.Builder(context)
                        .setView(root)
                        .setCancelable(false).show()
                    acceptButton.setOnClickListener {
                        RetrofitClient.getUser("${friendCodeEditText.text}") { _, response ->
                            val user = response.body()!!
                            if (user.id == User.ID_UNDEFINED) {
                                friendCodeEditText.setText("")
                                errorTextAddFriend.visibility = View.VISIBLE
                            } else {
                                showCheckDialog(user)
                                mBuilder.dismiss()
                            }
                        }
                    }
                    cancelButton.setOnClickListener {
                        mBuilder.dismiss()
                    }
                }
            }

            return root
        }
    }


    private fun showCheckDialog(user: User) {
        val dialogBind = DialogCheckFriendBinding.inflate(layoutInflater)
        with(dialogBind) {
            friendNameTextCheckFriend.text = user.nickname
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