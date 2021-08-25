package com.instructor.nedoollae.view.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.instructor.nedoollae.databinding.FragmentNoticeBinding
import com.instructor.nedoollae.databinding.FragmentReviewBinding

class ReviewFragment private constructor() : Fragment() {

    private lateinit var bind: FragmentReviewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentReviewBinding.inflate(inflater, container, false)
        with(bind) {
            return root
        }
    }

    companion object {
        fun newInstance() = ReviewFragment()
    }
}