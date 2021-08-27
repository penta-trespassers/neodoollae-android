package com.pentatrespassers.neodoollae.view.login.main.mypage.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pentatrespassers.neodoollae.databinding.FragmentTermsOfUseBinding

class TermsOfUseFragment private constructor() : Fragment() {

    private lateinit var bind: FragmentTermsOfUseBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentTermsOfUseBinding.inflate(inflater, container, false)
        with(bind) {
            return root
        }
    }

    companion object {
        fun newInstance() = TermsOfUseFragment().apply {
        }
    }


}