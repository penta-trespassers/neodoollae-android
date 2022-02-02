package com.pentatrespassers.neodoollae.view.login.main.mypage.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pentatrespassers.neodoollae.databinding.FragmentNotificationSettingsBinding

class NotificationSettingsFragment private constructor() : Fragment() {

    private lateinit var bind: FragmentNotificationSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentNotificationSettingsBinding.inflate(inflater, container, false)
        with(bind) {

            ns21.oneLineSettingSwitchConstraint.setOnClickListener {

            }

            return root
        }
    }

    companion object {
        fun newInstance() = NotificationSettingsFragment().apply {
        }
    }


}