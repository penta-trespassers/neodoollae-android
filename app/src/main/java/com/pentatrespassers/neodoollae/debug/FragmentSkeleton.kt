package com.pentatrespassers.neodoollae.debug

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pentatrespassers.neodoollae.databinding.FragmentHomeBinding
import splitties.fragmentargs.arg

class SkeletonFragment private constructor() : Fragment() {

    var test: Int by arg()

    private lateinit var bind: FragmentSkeletonBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentSkeletonBinding.inflate(inflater, container, false)
        with(bind) {
            return root
        }
    }

    companion object {
        fun newInstance(test: Int) = SkeletonFragment().apply {
            this.test = test
        }
    }


}