package com.pentatrespassers.neodoollae.view.login.mypage.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pentatrespassers.neodoollae.databinding.FragmentEditProfileBinding

class EditProfileFragment private constructor() : Fragment() {

    private lateinit var bind: FragmentEditProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentEditProfileBinding.inflate(inflater, container, false)
        with(bind) {
            return root
        }
    }

    companion object {
        fun newInstance() = EditProfileFragment().apply {
        }
    }


}