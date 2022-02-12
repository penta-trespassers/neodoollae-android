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

class FriendListFragment constructor() : Fragment() {


    private lateinit var bind: FragmentFriendListBinding

    lateinit var friendListAdapter: FriendListAdapter
    lateinit var favoriteUserAdapter: FriendListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentFriendListBinding.inflate(inflater, container, false)
        with(bind) {
            filteringText.setOnClickListener { filteringSwitch.toggle() }
            friendListAdapter = FriendListAdapter(requireContext())
            favoriteUserAdapter = FriendListAdapter(requireContext(), listOf(border1, favoriteText))
            friendListAdapter.init(favoriteUserAdapter, false)
            favoriteUserAdapter.init(friendListAdapter, true)
            friendListRecycler.adapter = friendListAdapter
            favoriteUserRecycler.adapter = favoriteUserAdapter
            friendSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    friendListAdapter.filter.filter(p0)
                    favoriteUserAdapter.filter.filter(p0)
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
            val users = response.body()!!
            favoriteUserAdapter.refresh(users[0])
            friendListAdapter.refresh(users[1])
        }
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)

        refreshFriendList()

    }


}