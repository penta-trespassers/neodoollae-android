package com.pentatrespassers.neodoollae.view.login.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pentatrespassers.neodoollae.databinding.FragmentHomeBinding
import splitties.fragmentargs.arg

class HomeFragment private constructor() : Fragment() {

    var test: Int by arg()

    private lateinit var bind: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentHomeBinding.inflate(inflater, container, false)
        with(bind) {
            dummyTextView.text = "$test"
            return root
        }
    }

    companion object {
        fun newInstance(test: Int) = HomeFragment().apply {
            this.test = test
        }
    }


}