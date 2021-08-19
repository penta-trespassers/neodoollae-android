package com.pentatrespassers.neodoollae.view.login


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.databinding.FragmentNoticeBinding
import com.pentatrespassers.neodoollae.view.login.notice.NoticeCellRecyclerViewAdapter
//import com.pentatrespassers.neodoollae.view.login.notice.LinearLayoutManagerWithSmoothScroller

class NoticeFragment private constructor() : Fragment() {

    private lateinit var bind: FragmentNoticeBinding

    //variables for expandable cell
    private lateinit var recyclerView: RecyclerView
    private lateinit var delete: Button
    private lateinit var deleteText: TextView // Text 터치시에도 delete 기능해야함
    private lateinit var spinner: Spinner
    //dummy dataArrayList  //private var dataList = ArrayList<Custom Class>()
    private var dataList = arrayListOf("예약 수락", "예약 거절", "예약 대기", "예약 신청", "예약 대기")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentNoticeBinding.inflate(inflater, container, false)
        with(bind) {

            //implement spinner
            spinner = filterSpinnerNotice
            ArrayAdapter.createFromResource(
                requireContext(),
                R.array.spinnerArrayNotice,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // Apply the adapter to the spinner
                spinner.adapter = adapter
            }

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(p0: AdapterView<*>?) { }
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                    when(position) {
                        0 -> {
                            //전체보기
                        }
                        1 -> {
                            //내 예약만
                        }
                        2 -> {
                            //내 방 예약만
                        }
                    }
                }
            }

            //implement expandable cell
            recyclerView = recyclerNotice
            recyclerView.apply{
                /*
                // 선택한 Cell이 RecyclerView의 TOP으로 가게 하는 Custom LinearLayoutManager 사용
                var linearLayoutManager = LinearLayoutManagerWithSmoothScroller(
                    container?.getContext(),
                    LinearLayoutManager.VERTICAL,
                    false
                )
                */

                // 안드로이드 기본 LinearLayoutManager
                var linearLayoutManager = LinearLayoutManager(container?.getContext())
                setHasFixedSize(true)
                this.layoutManager = linearLayoutManager
                var childControlRecyclerViewAdapter = NoticeCellRecyclerViewAdapter(this)
                childControlRecyclerViewAdapter.addItem(dataList)
                adapter = childControlRecyclerViewAdapter
            }
            return root
        }
    }

    companion object {
        fun newInstance() = NoticeFragment()
    }
}