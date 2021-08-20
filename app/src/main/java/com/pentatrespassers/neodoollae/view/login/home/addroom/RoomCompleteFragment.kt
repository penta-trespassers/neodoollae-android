package com.pentatrespassers.neodoollae.view.login.home.addroom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pentatrespassers.neodoollae.databinding.FragmentRoomCompleteBinding

class RoomCompleteFragment private constructor() : Fragment() {

    private lateinit var bind: FragmentRoomCompleteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentRoomCompleteBinding.inflate(inflater, container, false)
        with(bind) {
            return root
        }
    }

    companion object {
        fun newInstance() = RoomCompleteFragment()
    }
}