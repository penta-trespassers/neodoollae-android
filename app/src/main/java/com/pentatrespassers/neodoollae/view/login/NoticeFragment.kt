package com.pentatrespassers.neodoollae.view.login


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.databinding.FragmentNoticeBinding
import com.pentatrespassers.neodoollae.dto.Reservation
import com.pentatrespassers.neodoollae.view.login.notice.JinhaAdapter

//import com.pentatrespassers.neodoollae.view.login.notice.LinearLayoutManagerWithSmoothScroller

class NoticeFragment private constructor() : Fragment() {

    private lateinit var bind: FragmentNoticeBinding

    //dummy dataArrayList  //private var dataList = ArrayList<Reservation>()
    private var reservationList = arrayListOf(
        Reservation(nickname = "윤건우", status = Reservation.STATUS_WAITING),
        Reservation(nickname = "이서진", status = Reservation.STATUS_DECLINED),
        Reservation(nickname = "현수빈", status = Reservation.STATUS_ACCEPTED),
        Reservation(nickname = "김성준", status = Reservation.STATUS_WAITING),
        Reservation(nickname = "황진하", status = Reservation.STATUS_ACCEPTED)
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentNoticeBinding.inflate(inflater, container, false)
        with(bind) {
            recyclerNotice.setHasFixedSize(true)
            recyclerNotice.adapter = JinhaAdapter(requireContext(), reservationList)

            //implement spinner
            ArrayAdapter.createFromResource(
                requireContext(),
                R.array.spinnerArrayNotice,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // Apply the adapter to the spinner
                filterSpinnerNotice.adapter = adapter
            }

            filterSpinnerNotice.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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
            return root
        }
    }

    companion object {
        fun newInstance() = NoticeFragment()
    }
}