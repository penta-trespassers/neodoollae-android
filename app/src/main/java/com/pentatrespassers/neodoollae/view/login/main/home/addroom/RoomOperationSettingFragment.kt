package com.instructor.nedoollae.view.login.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.instructor.nedoollae.databinding.FragmentAddressBinding
import com.instructor.nedoollae.databinding.FragmentRoomOperationBinding

class RoomOperationSettingFragment private constructor() : Fragment() {

    private lateinit var bind: FragmentRoomOperationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentRoomOperationBinding.inflate(inflater, container, false)
        with(bind) {
            return root
        }
    }

    companion object {
        fun newInstance() = RoomOperationSettingFragment()
    }
}