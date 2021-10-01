package com.pentatrespassers.neodoollae.view.login.main.friend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pentatrespassers.neodoollae.databinding.FragmentFriendListBinding
import com.pentatrespassers.neodoollae.dto.User
import com.pentatrespassers.neodoollae.network.RetrofitClient
import com.pentatrespassers.neodoollae.view.login.main.friend.friendlist.FriendListAdapter

class FriendListFragment private constructor() : Fragment() {


    private lateinit var bind: FragmentFriendListBinding
    private val friendList: ArrayList<User> = arrayListOf()
    private val friendListAdapter by lazy { FriendListAdapter(requireContext(), friendList) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentFriendListBinding.inflate(inflater, container, false)
        with(bind) {
            friendListRecycler.adapter = friendListAdapter
            refreshFriendList()
            return root
        }
    }

    companion object {
        fun newInstance() = FriendListFragment().apply {
        }
    }

    private fun refreshFriendList() {
        RetrofitClient.getAllFriends { _, response ->
            friendListAdapter.refresh(response.body()!!)
        }
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (hidden) {
            refreshFriendList()
        }
    }

}