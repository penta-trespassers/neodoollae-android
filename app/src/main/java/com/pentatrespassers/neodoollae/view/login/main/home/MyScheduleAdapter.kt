package com.pentatrespassers.neodoollae.view.login.main.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pentatrespassers.neodoollae.databinding.CellMyScheduleBinding
import com.pentatrespassers.neodoollae.dto.Reservation
import com.pentatrespassers.neodoollae.view.login.main.home.myschedule.DayScheduleAdapter
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class MyScheduleAdapter(private val context: Context, ) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val schedules: ArrayList<Pair<String, ArrayList<Reservation>>> = arrayListOf()
    private val dateTimeFormatter = DateTimeFormatter.ofPattern("MM.dd E")

    inner class MyScheduleHolder(private val bind: CellMyScheduleBinding) :
        RecyclerView.ViewHolder(bind.root) {
        fun binding(schedule: Pair<String, ArrayList<Reservation>>) {
            with(bind) {
                localDateText.text = LocalDate.parse(schedule.first).format(dateTimeFormatter)
                dayScheduleRecycler.adapter = DayScheduleAdapter(context, schedule.second)
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
        return schedules.size
    }

    fun refresh(newSchedules: List<Pair<String, ArrayList<Reservation>>>? = null) {
        newSchedules?.let {
            schedules.clear()
            schedules.addAll(newSchedules)
        }
        notifyDataSetChanged()
    }


}