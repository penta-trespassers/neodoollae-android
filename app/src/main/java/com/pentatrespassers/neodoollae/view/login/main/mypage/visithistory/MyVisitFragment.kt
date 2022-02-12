package com.pentatrespassers.neodoollae.view.login.main.mypage.visithistory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pentatrespassers.neodoollae.databinding.FragmentMyVisitBinding
import com.pentatrespassers.neodoollae.network.RetrofitClient
import com.pentatrespassers.neodoollae.view.login.main.mypage.visithistory.myvisit.MyVisitAdapter

class MyVisitFragment constructor(): Fragment() {

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
            refreshMyVisit()
            return root
        }
    }

    companion object {
        fun newInstance() = MyVisitFragment().apply {
        }
    }

    fun refreshMyVisit() {
        RetrofitClient.getAllMyReservations { _, response ->
            myVisitAdapter.refresh(response.body()!!)
        }
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        refreshMyVisit()
    }


}