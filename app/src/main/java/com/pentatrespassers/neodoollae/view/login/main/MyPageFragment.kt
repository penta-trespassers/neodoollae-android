package com.pentatrespassers.neodoollae.view.login.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pentatrespassers.neodoollae.databinding.FragmentMyPageBinding
import com.pentatrespassers.neodoollae.view.login.main.mypage.SettingsActivity
import splitties.fragments.start

class MyPageFragment private constructor() : Fragment() {

    private lateinit var bind: FragmentMyPageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentMyPageBinding.inflate(inflater, container, false)
        with(bind) {

            myRoomHistoryButton.setOnClickListener {
                // start<Activity>()
            }
            myHistoryButton.setOnClickListener {
                // start<Activity>()
            }
            myRoomReservationButton.setOnClickListener {
                // start<Activity>()
            }
            myReservationButton.setOnClickListener {
                // start<Activity>()
            }
            SettingImageView.setOnClickListener {
                 start<SettingsActivity>()
            }

            return root
        }
    }

    companion object {
        fun newInstance() = MyPageFragment()
    }


}