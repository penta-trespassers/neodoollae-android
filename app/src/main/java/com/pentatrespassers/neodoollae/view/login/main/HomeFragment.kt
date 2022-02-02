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
import com.pentatrespassers.neodoollae.network.RetrofitClient
import com.pentatrespassers.neodoollae.view.login.main.home.MyScheduleAdapter


private const val MAX_ITEM_COUNT = 7

class HomeFragment private constructor() : Fragment() {


    private lateinit var bind: FragmentHomeBinding

    private lateinit var myScheduleAdapter: MyScheduleAdapter

    private val roomCardAdapter by lazy {

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentHomeBinding.inflate(inflater, container, false)
        with(bind) {
            RetrofitClient.getMySchedules(MAX_ITEM_COUNT) { _, response ->
                myScheduleAdapter = MyScheduleAdapter(requireContext(), response.body()!!.toList(), MAX_ITEM_COUNT)
                myScheduleRecycler.adapter = myScheduleAdapter
            }
            RetrofitClient.getRoom(Authentication.user?.id ?: -1) { _, response ->
                val roomList = response.body()!!
                roomList.add(Room(status = Room.STATUS_UNDEFINED))
                roomCardRecyclerHome.adapter = RoomCardAdapter(requireContext(), roomList)
            }
            return root
        }
    }

    companion object {
        fun newInstance() = HomeFragment()
    }


}