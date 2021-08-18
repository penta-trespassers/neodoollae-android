package com.pentatrespassers.neodoollae.view.login.around

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.pentatrespassers.neodoollae.databinding.FragmentAroundBinding
import com.pentatrespassers.neodoollae.databinding.FragmentRoomInfoBinding

class MapFragment private constructor() : Fragment() {

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
        fun newInstance() = MapFragment()
    }
}


