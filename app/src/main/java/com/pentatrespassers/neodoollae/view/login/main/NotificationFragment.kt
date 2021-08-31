package com.pentatrespassers.neodoollae.view.login.main


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.databinding.FragmentNotificationBinding
import com.pentatrespassers.neodoollae.dto.Notification
import com.pentatrespassers.neodoollae.view.login.main.notification.NotificationRecyclerViewAdapter

class NotificationFragment private constructor() : Fragment() {

    private lateinit var bind: FragmentNotificationBinding

    private var notificationList = arrayListOf(
        Notification(nickname = "윤건우", time = "2021.08.28", status = Notification.STATUS_RESERVE_WAITING),
        Notification(nickname = "이서진", time = "2021.08.28", status = Notification.STATUS_RESERVE_ACCEPTED),
        Notification(nickname = "현수빈", time = "2021.08.28", status = Notification.STATUS_RESERVE_DECLINED),
        Notification(nickname = "김성준", time = "2021.08.28", status = Notification.STATUS_REVIEW_HOST),
        Notification(nickname = "황진하", time = "2021.08.28", status = Notification.STATUS_REVIEW_GUEST),
        Notification(nickname = "에오스", time = "2021.08.28", status = Notification.STATUS_REVIEW_ROOM)
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentNotificationBinding.inflate(inflater, container, false)
        with(bind) {
            recyclerNotice.setHasFixedSize(true)
            recyclerNotice.adapter = NotificationRecyclerViewAdapter(requireContext(), notificationList)
            recyclerNotice.addItemDecoration(DividerItemDecoration(requireContext(), 1))

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
        fun newInstance() = NotificationFragment()
    }
}