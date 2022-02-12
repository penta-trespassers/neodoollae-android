package com.pentatrespassers.neodoollae.view.login.main.mypage.managereview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pentatrespassers.neodoollae.databinding.FragmentWritableReviewBinding
import com.pentatrespassers.neodoollae.databinding.FragmentWrittenReviewBinding
import com.pentatrespassers.neodoollae.view.login.main.mypage.managereview.writablereview.WritableReviewAdapter
import com.pentatrespassers.neodoollae.view.login.main.mypage.managereview.writtenreview.WrittenReviewAdapter

class WrittenReviewFragment private constructor() : Fragment() {

    private lateinit var bind: FragmentWrittenReviewBinding

    lateinit var writtenReviewAdapter: WrittenReviewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentWrittenReviewBinding.inflate(inflater, container, false)
        with(bind) {
            writtenReviewAdapter = WrittenReviewAdapter(requireContext())
            writtenReviewRecycler.adapter = writtenReviewAdapter

            return root
        }
    }

    companion object {
        fun newInstance() = WrittenReviewFragment().apply {
        }
    }

    fun refreshWrittenReview() {

    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (hidden) {
            refreshWrittenReview()
        }
    }


}