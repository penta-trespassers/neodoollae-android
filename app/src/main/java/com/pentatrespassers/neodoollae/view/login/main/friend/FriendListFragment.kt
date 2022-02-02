package com.pentatrespassers.neodoollae.view.login.main.friend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.pentatrespassers.neodoollae.databinding.FragmentFriendListBinding
import com.pentatrespassers.neodoollae.network.RetrofitClient
import com.pentatrespassers.neodoollae.view.login.main.friend.friendlist.FriendListAdapter

class FriendListFragment private constructor() : Fragment() {


    private lateinit var bind: FragmentFriendListBinding
    private val friendListAdapter by lazy { FriendListAdapter(requireContext(), arrayListOf()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentFriendListBinding.inflate(inflater, container, false)
        with(bind) {
            friendListRecycler.adapter = friendListAdapter
            refreshFriendList()
            friendSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    friendListAdapter.filter.filter(p0)
                    return false
                }
            })

            return root
        }
    }

    companion object {
        fun newInstance() = FriendListFragment().apply {
        }
    }

    fun refreshFriendList() {
        RetrofitClient.getAllFriends { _, response ->
            friendListAdapter.refresh(response.body()!!)
            friendListAdapter.filter.filter("")
        }
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (hidden) {
            refreshFriendList()
        }
    }

}