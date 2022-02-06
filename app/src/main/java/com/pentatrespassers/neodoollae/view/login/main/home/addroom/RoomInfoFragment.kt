package com.pentatrespassers.neodoollae.view.login.main.home.addroom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.databinding.FragmentRoomInfoBinding
import splitties.toast.toast

class RoomInfoFragment private constructor() : Fragment() {

    private lateinit var bind: FragmentRoomInfoBinding

    val roomName
        get() = bind.roomNameEditText.text.toString()
    val description
        get() = bind.roomInfoEditText.text.toString()
    val maxGuest
        get() = bind.maxGuestNumber.text.toString().toInt()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentRoomInfoBinding.inflate(inflater, container, false)
        with(bind) {
            maxGuestNumber.text = 1.toString()
            plusButtonRoomInfo.setOnClickListener {
                maxGuestNumber.text = (maxGuest + 1).toString()
            }
            minusButtonRoomInfo.setOnClickListener {
                when(maxGuest == 1){
                    true -> toast(R.string.min_is_one)
                    false -> maxGuestNumber.text = (maxGuest - 1).toString()
                }
            }

            return root
        }
    }

    companion object {
        fun newInstance() = RoomInfoFragment()
    }
}