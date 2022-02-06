package com.pentatrespassers.neodoollae.view.login.main.home

import android.R
import android.os.Bundle
import android.widget.CalendarView
import android.widget.CalendarView.OnDateChangeListener
import androidx.appcompat.app.AppCompatActivity
import com.pentatrespassers.neodoollae.common.adapter.RoomCardAdapter
import com.pentatrespassers.neodoollae.databinding.ActivityEntireScheduleBinding
import com.pentatrespassers.neodoollae.lib.Util.gone
import com.pentatrespassers.neodoollae.lib.Util.show
import java.text.SimpleDateFormat
import java.util.*


class EntireScheduleActivity : AppCompatActivity() {

    private val bind by lazy {
        ActivityEntireScheduleBinding.inflate(layoutInflater)
    }


    private val favoriteRoomAdapter by lazy {
        RoomCardAdapter(this, arrayListOf(), arrayListOf({
            if (it.itemCount > 0) {
                bind.bookableCardSchedule.show()
            } else {
                bind.notBookableTextSchedule.show()
            }
        }))
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(bind) {
            setContentView(root)

            calendarView.setOnDateChangeListener(OnDateChangeListener { view, year, month, dayOfMonth ->
                val month = month + 1
                val calendar = Calendar.getInstance()
                calendar.set(year, month, dayOfMonth)
                val date = calendar.time
                val simpledateformat = SimpleDateFormat("EEEE", Locale.getDefault())
                val dayName: String = simpledateformat.format(date)

                dateTextSchedule.text = "$year.$month.$dayOfMonth.$dayName"

                backButtonSchedule.setOnClickListener {
                    finish()
                }
            })

        }
    }
}