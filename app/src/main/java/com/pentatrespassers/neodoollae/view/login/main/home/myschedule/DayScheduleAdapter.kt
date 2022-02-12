package com.pentatrespassers.neodoollae.view.login.main.home.myschedule

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pentatrespassers.neodoollae.databinding.CellDayScheduleBinding
import com.pentatrespassers.neodoollae.dto.Reservation
import com.pentatrespassers.neodoollae.lib.Authentication
import com.pentatrespassers.neodoollae.lib.Util
import com.pentatrespassers.neodoollae.lib.Util.setBackgroundColor


class DayScheduleAdapter(private val context: Context, private val reservationList: ArrayList<Reservation>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    inner class DayScheduleHolder(private val bind: CellDayScheduleBinding) :
        RecyclerView.ViewHolder(bind.root) {
        fun binding(reservation: Reservation) {
            with(bind) {
                if (reservation.userId == Authentication.uid) {
                    if (reservation.type == Reservation.TYPE_CHECK_IN) {
                        itemView.setBackgroundColor("#7986CB")
                        dayScheduleText.text = "내가 ${reservation.roomName} 입실"
                    } else {
                        itemView.setBackgroundColor("#E57373")
                        dayScheduleText.text = "내가 ${reservation.roomName} 퇴실"
                    }
                } else {
                    if (reservation.type == Reservation.TYPE_CHECK_IN) {
                        itemView.setBackgroundColor("#7986CB")
                        dayScheduleText.text = "${Util.ppString(reservation.nickname, "이", "가")} ${reservation.roomName} 입실"
                    } else {
                        itemView.setBackgroundColor("#E57373")
                        dayScheduleText.text = "${Util.ppString(reservation.nickname, "이", "가")} ${reservation.roomName} 퇴실"
                    }

                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        return DayScheduleHolder(CellDayScheduleBinding.inflate(layoutInflater, parent, false))
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as DayScheduleHolder).binding(reservationList[position])
    }

    override fun getItemCount(): Int {
        return reservationList.size
    }



}