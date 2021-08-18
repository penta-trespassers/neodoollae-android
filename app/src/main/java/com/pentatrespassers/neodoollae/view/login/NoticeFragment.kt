package com.pentatrespassers.neodoollae.view.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.databinding.FragmentNoticeBinding
import com.pentatrespassers.neodoollae.view.login.notice.ChildControlRecyclerViewAdapter
import com.pentatrespassers.neodoollae.view.login.notice.LinearLayoutManagerWithSmoothScroller

class NoticeFragment private constructor() : Fragment() {

    private lateinit var bind: FragmentNoticeBinding

    // cardview
    private var dataList = ArrayList<Int>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentNoticeBinding.inflate(inflater, container, false)
        // added

        dataList.add(1)
        dataList.add(1)
        dataList.add(1)
        dataList.add(1)


        var recyclerView = bind.childControlRecyclerView
        recyclerView.apply {
            var lm = LinearLayoutManagerWithSmoothScroller(
                container?.getContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
            setHasFixedSize(true)
            this.layoutManager = lm
            var childControlRecyclerViewAdapter = ChildControlRecyclerViewAdapter(this)
            childControlRecyclerViewAdapter.addItem(dataList)
            adapter = childControlRecyclerViewAdapter

        }

        with(bind) {

            return root
        }
    }


    companion object {
        fun newInstance() = NoticeFragment()
    }
}