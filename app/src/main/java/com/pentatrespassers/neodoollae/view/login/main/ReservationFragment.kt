package com.pentatrespassers.neodoollae.view.login.main


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.databinding.FragmentReservationBinding
import com.pentatrespassers.neodoollae.dto.Reservation
import com.pentatrespassers.neodoollae.dto.Room
import com.pentatrespassers.neodoollae.lib.Util.fragmentTransaction
import com.pentatrespassers.neodoollae.network.RetrofitClient
import com.pentatrespassers.neodoollae.view.login.MainActivity
import com.pentatrespassers.neodoollae.view.login.main.reservation.ReservationAdapter

class ReservationFragment private constructor() : Fragment() {

    private lateinit var bind: FragmentReservationBinding

    private val reservationAdapter by lazy {
        ReservationAdapter(requireContext(),
        makeDummyReservationData())
    }

//    private fun refreshReservationList() {
//        RetrofitClient.getAllMyReservations { _, response ->
//            reservationAdapter. = response.body()!!
//            reservationAdapter.notifyDataSetChanged()
//        }
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentReservationBinding.inflate(inflater, container, false)
        with(bind) {
         //   reservationRecycler.setHasFixedSize(true)
            reservationRecycler.adapter = reservationAdapter
           // reservationRecycler.addItemDecoration(DividerItemDecoration(requireContext(), 1))
           //refreshReservationList()

            //나의 예약을 누른 경우
            myReservationConstraint.setOnClickListener {

            }
            // 내 방 예약을 누른 경우
            myRoomReservationConstraint.setOnClickListener {

            }
            //대기중을 누른 경우
            waitingReservationConstraint.setOnClickListener {

            }



            return root
        }
    }

    fun makeDummyReservationData(): ArrayList<Reservation> {
        val data: ArrayList<Reservation> = arrayListOf(
            Reservation(1,1,1,"sunny","써니의 방",null,null,null,"샘플 데이터입니다.","샘플 response데이터입니다"),
            Reservation(2,1,1,"sunny","써니의 방",null,null,null,"샘플 데이터입니다.","샘플 response데이터입니다"),
            Reservation(3,1,1,"sunny","써니의 방",null,null,null,"샘플 데이터입니다.","샘플 response데이터입니다"),
            Reservation(4,1,1,"sunny","써니의 방",null,null,null,"샘플 데이터입니다.","샘플 response데이터입니다")


        )


        return data
    }

    companion object {
        fun newInstance() = ReservationFragment()
    }
}