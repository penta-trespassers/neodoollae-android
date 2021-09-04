package com.pentatrespassers.neodoollae.view.login.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pentatrespassers.neodoollae.databinding.FragmentMyPageBinding
import com.pentatrespassers.neodoollae.lib.Authentication
import com.pentatrespassers.neodoollae.view.login.main.mypage.*
import splitties.fragments.start

class MyPageFragment private constructor() : Fragment() {

    private lateinit var bind: FragmentMyPageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentMyPageBinding.inflate(inflater, container, false)
        with(bind) {

            nicknameTextMyPage.text = Authentication.user?.nickname
            friendCodeText.text = Authentication.user?.friendCode

            myRoomHistoryButton.setOnClickListener {
                start<RoomVisitTraceActivity> { }
            }
            myHistoryButton.setOnClickListener {
                start<UserVisitTraceActivity> { }
            }
            myRoomReservationButton.setOnClickListener {
                start<RoomBookInfoActivity> { }
            }
            myReservationButton.setOnClickListener {
                start<UserBookInfoActivity> { }
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