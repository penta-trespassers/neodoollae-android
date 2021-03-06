package com.pentatrespassers.neodoollae.view.login.main.mypage.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.databinding.FragmentManageAccountBinding
import com.pentatrespassers.neodoollae.lib.Util.setOneLineMenu
import com.pentatrespassers.neodoollae.view.LoginActivity
import splitties.fragments.start

class ManageAccountFragment constructor() : Fragment() {

    private lateinit var bind: FragmentManageAccountBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentManageAccountBinding.inflate(inflater, container, false)
        with(bind) {

            logoutCell.setOnClickListener{
                // TODO : LOGOUT
                start<LoginActivity>()
            }
            withdrawCell.setOnClickListener {
                // TODO : WITHDRAW
            }

            return root
        }
    }

    companion object {
        fun newInstance() = ManageAccountFragment().apply {
        }
    }


}