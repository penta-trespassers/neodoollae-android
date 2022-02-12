package com.pentatrespassers.neodoollae.view.login.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.databinding.FragmentNotificationBinding
import com.pentatrespassers.neodoollae.dto.Notification
import com.pentatrespassers.neodoollae.view.login.main.notification.NotificationAdapter

class NotificationFragment private constructor() : Fragment() {

    private lateinit var bind: FragmentNotificationBinding

    private val notificationList = arrayListOf(
        Notification("메세지 예약 기다림", "메세지 예약 기다림", 0, 0),
        Notification("메세지 예약 수락", "메세지 예약 수락", 0, 1),
        Notification("메세지 예약 거절", "메세지 예약 거절", 0, 2),
        Notification("메세지 방문 리뷰", "메세지 예액", 0, 3),
        Notification("메세지 손님 리뷰", "메세지 예액", 0, 4),
        Notification("메세지 친구 됨", "메세지 예액", 0, 5),
        Notification("메세지 친구 요청", "메세지 예액", 0, 6)
    )

    private val notificationAdapter by lazy {
        NotificationAdapter(requireContext(), notificationList)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentNotificationBinding.inflate(inflater, container, false)
        with(bind) {
            notificationRecycler.adapter = notificationAdapter

            filterSpinner.adapter =
                ArrayAdapter.createFromResource(
                    requireContext(),
                    R.array.spinnerArrayNotification,
                    android.R.layout.simple_spinner_item
                ).apply {
                    // Specify the layout to use when the list of choices appears
                    setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                }

            filterSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(p0: AdapterView<*>?) {}
                override fun onItemSelected(
                    p0: AdapterView<*>?,
                    p1: View?,
                    position: Int,
                    p3: Long
                ) {
                    when (position) {
                        0 -> {
                            // 전체 알림
                            notificationRecycler.adapter = notificationAdapter.selectType(0)
                        }
                        1 -> {
                            // 예약 신청
                            notificationRecycler.adapter = notificationAdapter.selectType(1)
                        }
                        2 -> {
                            // 리뷰 작성
                            notificationRecycler.adapter = notificationAdapter.selectType(2)
                        }
                        3 -> {
                            // 친구 성사
                            notificationRecycler.adapter = notificationAdapter.selectType(3)
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