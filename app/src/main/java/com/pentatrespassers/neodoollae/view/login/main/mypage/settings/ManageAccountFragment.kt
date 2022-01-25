package com.pentatrespassers.neodoollae.view.login.main.mypage.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.databinding.FragmentManageAccountBinding

class ManageAccountFragment private constructor() : Fragment() {

    private lateinit var bind: FragmentManageAccountBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentManageAccountBinding.inflate(inflater, container, false)
        with(bind) {
            with(logoutCell){
                oneLineSettingImage.setImageResource(R.drawable.ic_logout_black_24dp)
                oneLineSettingText.setText(R.string.logout)
            }
            with(withdrawCell){
                oneLineSettingImage.setImageResource(R.drawable.ic_cancel_24dp)
                oneLineSettingText.setText(R.string.withdraw)
            }

            logoutConstraint.setOnClickListener{

            }

            withdrawConstraint.setOnClickListener{

            }


            return root
        }
    }

    companion object {
        fun newInstance() = ManageAccountFragment().apply {
        }
    }


}