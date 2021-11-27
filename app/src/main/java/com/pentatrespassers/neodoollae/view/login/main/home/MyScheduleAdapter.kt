package com.pentatrespassers.neodoollae.view.login.main.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pentatrespassers.neodoollae.databinding.CellMyScheduleBinding
import com.pentatrespassers.neodoollae.dto.Reservation

class MyScheduleAdapter(private val context: Context, private val reservationList: ArrayList<Reservation>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class MyScheduleHolder(private val bind: CellMyScheduleBinding) :
        RecyclerView.ViewHolder(bind.root) {
        fun binding(reservation: Reservation) {
            with(bind) {

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        return MyScheduleHolder(CellMyScheduleBinding.inflate(layoutInflater, parent, false))
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val reservation = reservationList[position]
        (holder as MyScheduleHolder).binding(reservation)
    }

    override fun getItemCount(): Int {
        return reservationList.size
    }


}