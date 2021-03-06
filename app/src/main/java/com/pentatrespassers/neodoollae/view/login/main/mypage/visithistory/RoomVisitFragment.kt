package com.pentatrespassers.neodoollae.view.login.main.mypage.visithistory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pentatrespassers.neodoollae.databinding.FragmentRoomVisitBinding
import com.pentatrespassers.neodoollae.network.RetrofitClient
import com.pentatrespassers.neodoollae.view.login.main.mypage.visithistory.roomvisit.RoomVisitAdapter

class RoomVisitFragment constructor(): Fragment() {

    private lateinit var bind: FragmentRoomVisitBinding

    lateinit var roomVisitAdapter: RoomVisitAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentRoomVisitBinding.inflate(inflater, container, false)
        with(bind) {
            roomVisitAdapter = RoomVisitAdapter(requireContext())
            roomVisitRecycler.adapter = roomVisitAdapter
            refreshRoomVisit()
            return root
        }
    }

    companion object {
        fun newInstance() = RoomVisitFragment().apply {
        }
    }

    fun refreshRoomVisit() {
        RetrofitClient.getAllMyRoomReservations { _, response ->
            roomVisitAdapter.refresh(response.body()!!)
        }
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (hidden) {
            refreshRoomVisit()
        }
    }
}