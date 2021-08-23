package com.pentatrespassers.neodoollae.view.login.around

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.pentatrespassers.neodoollae.databinding.FragmentAroundBinding


class AroundFragment private constructor() : Fragment() {

    private lateinit var bind: FragmentAroundBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentAroundBinding.inflate(inflater, container, false)
        with(bind) {

            //mapview로 변경 후 뜨긴 뜨는데 작동안함
            mapViewAround.setOnClickListener {
                mapSearchViewAround.toggleVisibility()
                slidingLayout.toggleVisibility()
            }

            return root
        }



    }


    companion object {
        fun newInstance() = AroundFragment()
    }
    fun View.isVisible() = this.visibility == View.VISIBLE

    fun View.toggleVisibility() {
        if (this.isVisible()) {
            this.visibility = View.INVISIBLE
        } else {
            this.visibility = View.VISIBLE
        }
    }
}

