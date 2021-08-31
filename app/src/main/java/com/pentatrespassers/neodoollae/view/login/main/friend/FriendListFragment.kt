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
    lateinit var friendList: List<User>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentFriendListBinding.inflate(inflater, container, false)
        with(bind) {
            RetrofitClient.getAllFriends()
                .enqueue(RetrofitClient.defaultCallback { call, response ->
                    if (response.body() != null) {
                        friendList = response.body()!!
                        friendListRecycler.adapter = FriendListAdapter(requireContext(), friendList)
                    }
                })

            return root
        }
    }

    companion object {
        fun newInstance() = FriendListFragment().apply {
        }
    }


}