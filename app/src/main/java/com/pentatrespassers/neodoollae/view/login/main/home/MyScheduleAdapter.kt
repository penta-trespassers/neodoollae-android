package com.pentatrespassers.neodoollae.view.login.main.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pentatrespassers.neodoollae.databinding.CellMyScheduleBinding
import com.pentatrespassers.neodoollae.dto.Reservation
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class MyScheduleAdapter(private val context: Context, private var schedules: List<Pair<String, ArrayList<Reservation>>>, private val maxItemCount: Int) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val dateTimeFormatter = DateTimeFormatter.ofPattern("MM.dd E")

    inner class MyScheduleHolder(private val bind: CellMyScheduleBinding) :
        RecyclerView.ViewHolder(bind.root) {
        fun binding(schedule: Pair<String, ArrayList<Reservation>>) {
            with(bind) {
                localDateText.text = LocalDate.parse(schedule.first).format(dateTimeFormatter)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        return MyScheduleHolder(CellMyScheduleBinding.inflate(layoutInflater, parent, false))
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MyScheduleHolder).binding(schedules[position])
    }

    override fun getItemCount(): Int {
        return maxItemCount
    }



}