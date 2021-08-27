package com.pentatrespassers.neodoollae.view.login.main.mypage.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pentatrespassers.neodoollae.databinding.FragmentNoticeSettingsBinding

class NoticeSettingsFragment private constructor() : Fragment() {

    private lateinit var bind: FragmentNoticeSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentNoticeSettingsBinding.inflate(inflater, container, false)
        with(bind) {
            return root
        }
    }

    companion object {
        fun newInstance() = NoticeSettingsFragment().apply {
        }
    }


}