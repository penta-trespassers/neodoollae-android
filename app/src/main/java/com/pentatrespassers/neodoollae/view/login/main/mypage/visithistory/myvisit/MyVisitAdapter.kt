package com.pentatrespassers.neodoollae.view.login.main.mypage.visithistory.myvisit

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pentatrespassers.neodoollae.databinding.CellReservationBinding
import com.pentatrespassers.neodoollae.dto.Reservation
import com.pentatrespassers.neodoollae.lib.Util

class MyVisitAdapter(private var context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private var historyList: ArrayList<Reservation> = arrayListOf()
    val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    val formatter1 = Util.getDateFormatter("yyyy.MM.dd")
    val formatter2 = Util.getDateFormatter("a hh:mm")

    inner class CellMyVisitHolder(private val bind: CellReservationBinding) :
        RecyclerView.ViewHolder(bind.root) {
        fun binding(reservation: Reservation) {
            with(bind) {
                reservationRoomText.text = reservation.roomName
                createdAtText.text = reservation.hostname // 이거 hostname맞나?

                reservationStartDateText.text = formatter1.format(reservation.checkIn!!)
                reservationStartTimeText.text = formatter2.format(reservation.checkIn!!)

                reservationEndDateText.text = formatter1.format(reservation.checkOut!!)
                reservationEndTimeText.text = formatter2.format(reservation.checkOut!!)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CellMyVisitHolder(
            CellReservationBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = historyList[position]
        (holder as MyVisitAdapter.CellMyVisitHolder).binding(data)
    }


    fun refresh(historyList: ArrayList<Reservation>) {
        this.historyList = historyList
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return historyList.size
    }

}