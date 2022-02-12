package com.pentatrespassers.neodoollae.view.login.main.friend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pentatrespassers.neodoollae.databinding.FragmentFriendRequestBinding
import com.pentatrespassers.neodoollae.network.RetrofitClient
import com.pentatrespassers.neodoollae.view.login.main.FriendFragment
import com.pentatrespassers.neodoollae.view.login.main.friend.friendrequest.FriendRequestAdapter

class FriendRequestFragment constructor() : Fragment() {


    private lateinit var bind: FragmentFriendRequestBinding
    private val friendRequestAdapter by lazy {
        FriendRequestAdapter(
            requireContext(),
            arrayListOf(),
            requireParentFragment() as FriendFragment
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentFriendRequestBinding.inflate(inflater, container, false)
        with(bind) {
            friendRequestRecycler.adapter = friendRequestAdapter
            refreshFriendRequest()
            return root
        }
    }

    fun refreshFriendRequest() {
        RetrofitClient.getAllFriendRequests { _, response ->
            val friendList = response.body()!!
            friendRequestAdapter.refresh(friendList)
        }
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (hidden) {
            refreshFriendRequest()
        }
    }

    companion object {
        fun newInstance() = FriendRequestFragment()
    }


}