package com.pentatrespassers.neodoollae.view.login.main.mypage.visithistory.roomvisit

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pentatrespassers.neodoollae.databinding.CellReservationBinding
import com.pentatrespassers.neodoollae.dto.Reservation
import com.pentatrespassers.neodoollae.view.login.main.reservation.ReservationAdapter

class RoomVisitAdapter (private var context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private var historys: ArrayList<Reservation> = arrayListOf()
    val layoutInflater = LayoutInflater.from(context)

    inner class CellRoomVisitHolder(private val bind: CellReservationBinding) :
        RecyclerView.ViewHolder(bind.root) {
        fun binding(reservation: Reservation) {
            with(bind) {
                reservationRoomText.text = reservation.nickname // 손님 이름 넣어야 함 -> 추가해준다고 합니다
                createdAtText.text = reservation.nickname // 방 주인 이름

                reservationEndTimeText.text = "오전 YY:MM"
                reservationStartTimeText.text = "오후 YY:MM"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CellRoomVisitHolder(
            CellReservationBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = historys[position]
        (holder as RoomVisitAdapter.CellRoomVisitHolder).binding(data)
    }


    fun refresh(historyList: ArrayList<Reservation>) {
        this.historys = historyList
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return historys.size
    }
}