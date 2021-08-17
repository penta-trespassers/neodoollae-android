package com.pentatrespassers.neodoollae.view.login.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pentatrespassers.neodoollae.databinding.FragmentRoomInfoBinding

class RoomInfoFragment private constructor() : Fragment() {

    private lateinit var bind: FragmentRoomInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentRoomInfoBinding.inflate(inflater, container, false)
        with(bind) {
            return root
        }
    }

    companion object {
        fun newInstance() = RoomInfoFragment()
    }
}