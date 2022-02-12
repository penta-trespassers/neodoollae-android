package com.pentatrespassers.neodoollae.view.login.main.mypage.managereview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pentatrespassers.neodoollae.databinding.FragmentWritableReviewBinding
import com.pentatrespassers.neodoollae.view.login.main.mypage.managereview.writablereview.WritableReviewAdapter

class WritableReviewFragment constructor() : Fragment() {

    private lateinit var bind: FragmentWritableReviewBinding

    lateinit var writableReviewAdapter: WritableReviewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentWritableReviewBinding.inflate(inflater, container, false)
        with(bind) {
            writableReviewAdapter = WritableReviewAdapter(requireContext())
            writableReviewRecycler.adapter = writableReviewAdapter

            return root
        }
    }

    companion object {
        fun newInstance() = WritableReviewFragment().apply {
        }
    }

    fun refreshWritableReview() {

    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (hidden) {
            refreshWritableReview()
        }
    }

}