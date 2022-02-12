package com.pentatrespassers.neodoollae.view.login.main.mypage.visithistory.myvisit

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pentatrespassers.neodoollae.databinding.CellReservationBinding
import com.pentatrespassers.neodoollae.databinding.CellWritableReviewBinding
import com.pentatrespassers.neodoollae.dto.Reservation
import com.pentatrespassers.neodoollae.lib.Authentication
import com.pentatrespassers.neodoollae.view.login.main.reservation.ReservationAdapter
import com.pentatrespassers.neodoollae.view.login.main.reservation.ReservationAdapter.CellReservationHolder

class MyVisitAdapter(private var context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private var historys: ArrayList<Reservation> = arrayListOf()
    val layoutInflater = LayoutInflater.from(context)

    inner class CellMyVisitHolder(private val bind: CellReservationBinding) :
        RecyclerView.ViewHolder(bind.root) {
        fun binding(reservation: Reservation) {
            with(bind) {
                reservationRoomText.text = reservation.roomName
                createdAtText.text = reservation.nickname // 이거 hostname맞나?

                reservationEndTimeText.text = "오전 YY:MM"
                reservationStartTimeText.text = "오후 YY:MM"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CellMyVisitHolder(
            CellReservationBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = historys[position]
        (holder as MyVisitAdapter.CellMyVisitHolder).binding(data)
    }


    fun refresh(historyList: ArrayList<Reservation>) {
        this.historys = historyList
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return historys.size
    }

}