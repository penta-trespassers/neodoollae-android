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
import com.pentatrespassers.neodoollae.lib.Util
import com.pentatrespassers.neodoollae.network.RetrofitClient

class HomeFragment private constructor() : Fragment() {


    private lateinit var bind: FragmentHomeBinding


    private val roomCardAdapter by lazy {

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentHomeBinding.inflate(inflater, container, false)
        with(bind) {
            RetrofitClient.getRoom(Authentication.user?.id ?: -1) { _, response ->
                Util.j("$response")
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