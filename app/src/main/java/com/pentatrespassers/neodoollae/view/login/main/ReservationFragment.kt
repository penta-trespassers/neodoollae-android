package com.pentatrespassers.neodoollae.view.login.main


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.databinding.FragmentReservationBinding
import com.pentatrespassers.neodoollae.lib.Util.hide
import com.pentatrespassers.neodoollae.lib.Util.show
import com.pentatrespassers.neodoollae.network.RetrofitClient
import com.pentatrespassers.neodoollae.view.login.main.reservation.ReservationAdapter
import splitties.resources.color

class ReservationFragment constructor() : Fragment() {

    private lateinit var bind: FragmentReservationBinding

    private val reservationAdapter by lazy {
        ReservationAdapter(requireContext())
    }

    private val views: HashMap<ConstraintLayout, Pair<TextView, ConstraintLayout>> = hashMapOf()

    private fun changeTo(constraintLayout: ConstraintLayout) {
        views.values.forEach {
            it.first.setTextColor(color(R.color.trespassGray_900))
            it.second.hide()
        }
        views.getValue(constraintLayout).let {
            it.first.setTextColor(color(R.color.trespassBlue_900))
            it.second.show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentReservationBinding.inflate(inflater, container, false)
        with(bind) {
            reservationRecycler.adapter = reservationAdapter

            RetrofitClient.getAllMyReservations { _, response ->
                reservationAdapter.refresh(response.body()!!)
            }

            views[myReservationConstraint] =
                Pair(myReservationText, myReservationUnderlineConstraint)
            views[myRoomReservationConstraint] =
                Pair(myRoomReservationText, myRoomReservationUnderlineConstraint)
            views[waitingReservationConstraint] =
                Pair(waitingRerservationText, waitingReservationUnderlineConstraint)


            //나의 예약을 누른 경우
            myReservationConstraint.setOnClickListener {
                RetrofitClient.getAllMyReservations { _, response ->
                    reservationAdapter.refresh(response.body()!!)
                }
                changeTo(myReservationConstraint)
            }
            // 내 방 예약을 누른 경우
            myRoomReservationConstraint.setOnClickListener {
                RetrofitClient.getAllMyRoomReservations { _, response ->
                    reservationAdapter.refresh(response.body()!!)
                }
                changeTo(myRoomReservationConstraint)
            }
            //대기중을 누른 경우
            waitingReservationConstraint.setOnClickListener {
                changeTo(waitingReservationConstraint)
            }



            return root
        }
    }


    companion object {
        fun newInstance() = ReservationFragment()
    }
}