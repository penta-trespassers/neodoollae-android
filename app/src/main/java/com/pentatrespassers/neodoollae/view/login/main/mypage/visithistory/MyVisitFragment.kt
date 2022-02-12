package com.pentatrespassers.neodoollae.view.login.main.mypage.visithistory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pentatrespassers.neodoollae.databinding.FragmentMyVisitBinding
import com.pentatrespassers.neodoollae.databinding.FragmentWritableReviewBinding
import com.pentatrespassers.neodoollae.view.login.main.mypage.managereview.WritableReviewFragment
import com.pentatrespassers.neodoollae.view.login.main.mypage.managereview.writablereview.WritableReviewAdapter
import com.pentatrespassers.neodoollae.view.login.main.mypage.visithistory.myvisit.MyVisitAdapter

class MyVisitFragment private constructor(): Fragment() {

    private lateinit var bind: FragmentMyVisitBinding

    lateinit var myVisitAdapter: MyVisitAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentMyVisitBinding.inflate(inflater, container, false)
        with(bind) {
            myVisitAdapter = MyVisitAdapter(requireContext())
            myVisitRecycler.adapter = myVisitAdapter

            return root
        }
    }

    companion object {
        fun newInstance() = MyVisitFragment().apply {
        }
    }

    fun refreshMyVisit() {

    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (hidden) {
            refreshMyVisit()
        }
    }


}