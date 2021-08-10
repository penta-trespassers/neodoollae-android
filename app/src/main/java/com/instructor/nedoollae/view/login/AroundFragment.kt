package com.instructor.nedoollae.view.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.instructor.nedoollae.databinding.FragmentAroundBinding

class AroundFragment private constructor() : Fragment() {

    private lateinit var bind: FragmentAroundBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentAroundBinding.inflate(inflater, container, false)
        with(bind) {
            return root
        }
    }

    companion object {
        fun newInstance() = AroundFragment()
    }
}