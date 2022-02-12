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

class NotificationFragment constructor() : Fragment() {

    private lateinit var bind: FragmentNotificationBinding

    private val notificationList = arrayListOf(
        Notification("황진하님의 예약 신청이 있습니다.", "메세지 예약 기다림", 0, 0),
        Notification("현수빈님이 예약 신청을 거절했습니다.", "메세지 예약 수락", 0, 1),
        Notification("착한유저님이 예약 신청을 수락했습니다.", "메세지 예약 거절", 0, 2),
        Notification("진하의 방문 리뷰를 작성해주세요.", "메세지 예액", 0, 3),
        Notification("관리자2의 손님 리뷰를 작성해주세요.", "메세지 예액", 0, 4),
        Notification("서진님의 친구 요청이 있습니다.", "메세지 예액", 0, 5),
        Notification("현수빈님과 친구가 되었습니다.", "메세지 예액", 0, 6),
        Notification("성준님이 친구 요청을 거절했습니다.", "메세지 예액", 0, 6)
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