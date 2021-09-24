package com.pentatrespassers.neodoollae.view.login.main.friend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pentatrespassers.neodoollae.databinding.FragmentFriendRequestBinding
import com.pentatrespassers.neodoollae.network.RetrofitClient
import com.pentatrespassers.neodoollae.view.login.main.friend.friendrequest.FriendRequestAdapter

class FriendRequestFragment private constructor() : Fragment() {


    private lateinit var bind: FragmentFriendRequestBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentFriendRequestBinding.inflate(inflater, container, false)
        with(bind) {
            RetrofitClient.getAllFriendRequests { _, response ->
                if (response.body() != null) {
                    friendRequestRecycler.adapter =
                        FriendRequestAdapter(requireContext(), response.body()!!)
                }
            }
            return root
        }
    }

    companion object {
        fun newInstance() = FriendRequestFragment()
    }


}