package com.pentatrespassers.neodoollae.view.login.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pentatrespassers.neodoollae.common.adapter.RoomCardAdapter
import com.pentatrespassers.neodoollae.databinding.FragmentHomeBinding
import com.pentatrespassers.neodoollae.dto.Room
import com.pentatrespassers.neodoollae.lib.Authentication
import com.pentatrespassers.neodoollae.lib.Util.gone
import com.pentatrespassers.neodoollae.lib.Util.show
import com.pentatrespassers.neodoollae.network.RetrofitClient
import com.pentatrespassers.neodoollae.view.login.main.home.MyScheduleAdapter


class HomeFragment constructor() : Fragment() {


    private lateinit var bind: FragmentHomeBinding


    private val myScheduleAdapter by lazy {
        MyScheduleAdapter(requireContext())
    }

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



            myScheduleRecycler.adapter = myScheduleAdapter
            favoriteRoomRecycler.adapter = favoriteRoomAdapter
            myRoomRecycler.adapter = myRoomAdapter

            refresh()


            return root
        }
    }

    companion object {
        fun newInstance() = HomeFragment()
        const val MAX_ITEM_COUNT = 7
    }

    fun refresh() {
        RetrofitClient.getMySchedules(MAX_ITEM_COUNT) { _, response ->
            myScheduleAdapter.refresh(response.body()!!.toList())
        }

        RetrofitClient.getMyFavoriteRooms { _, response ->
            favoriteRoomAdapter.refresh(response.body())
        }

        RetrofitClient.getRoom(Authentication.user?.id ?: -1) { _, response ->
            myRoomAdapter.refresh(response.body()?.apply {
                reverse()
                add(Room(status = Room.STATUS_UNDEFINED))
            })
        }
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
    }


}