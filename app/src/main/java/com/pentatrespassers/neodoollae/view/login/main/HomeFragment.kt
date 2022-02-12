package com.pentatrespassers.neodoollae.view.login.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.dynamiclinks.PendingDynamicLinkData
import com.google.firebase.dynamiclinks.ktx.androidParameters
import com.google.firebase.dynamiclinks.ktx.dynamicLink
import com.google.firebase.dynamiclinks.ktx.dynamicLinks
import com.google.firebase.dynamiclinks.ktx.socialMetaTagParameters
import com.google.firebase.ktx.Firebase
import com.pentatrespassers.neodoollae.common.adapter.RoomCardAdapter
import com.pentatrespassers.neodoollae.databinding.FragmentHomeBinding
import com.pentatrespassers.neodoollae.dto.Room
import com.pentatrespassers.neodoollae.lib.Authentication
import com.pentatrespassers.neodoollae.lib.Util
import com.pentatrespassers.neodoollae.lib.Util.gone
import com.pentatrespassers.neodoollae.lib.Util.show
import com.pentatrespassers.neodoollae.network.RetrofitClient
import com.pentatrespassers.neodoollae.view.login.main.home.MyScheduleAdapter


class HomeFragment constructor() : Fragment() {


    private lateinit var bind: FragmentHomeBinding

    private lateinit var myScheduleAdapter: MyScheduleAdapter

    private val favoriteRoomAdapter by lazy {
        RoomCardAdapter(requireContext(), arrayListOf(), arrayListOf({
            if (it.itemCount > 0) {
                bind.favoriteRoomConstraint.show()
            } else {
                bind.favoriteRoomConstraint.gone()
            }
        }))
    }

    private val myRoomAdapter by lazy {
        RoomCardAdapter(requireContext(), arrayListOf(Room(status = Room.STATUS_UNDEFINED)))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentHomeBinding.inflate(inflater, container, false)
        with(bind) {


            RetrofitClient.getMySchedules(MAX_ITEM_COUNT) { _, response ->
                myScheduleAdapter = MyScheduleAdapter(requireContext(), response.body()!!.toList())
                myScheduleRecycler.adapter = myScheduleAdapter
            }

            favoriteRoomRecycler.adapter = favoriteRoomAdapter
            myRoomRecycler.adapter = myRoomAdapter

            RetrofitClient.getMyFavoriteRooms { _, response ->
                favoriteRoomAdapter.refresh(response.body())
            }

            RetrofitClient.getRoom(Authentication.user?.id ?: -1) { _, response ->
                myRoomAdapter.refresh(response.body()?.apply {
                    add(Room(status = Room.STATUS_UNDEFINED))
                })
            }

            return root
        }
    }

    companion object {
        fun newInstance() = HomeFragment()
        const val MAX_ITEM_COUNT = 7
    }


}