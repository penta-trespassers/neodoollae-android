package com.pentatrespassers.neodoollae.view.login.main.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pentatrespassers.neodoollae.common.adapter.RoomCardAdapter
import com.pentatrespassers.neodoollae.databinding.ActivityEntireScheduleBinding
import com.pentatrespassers.neodoollae.lib.Util
import com.pentatrespassers.neodoollae.lib.Util.show
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

            backButtonSchedule.setOnClickListener {
                onBackPressed()
            }
            val calendar = Calendar.getInstance()
            val formatter = Util.getDateFormatter("yy MM dd EEEE")
            dateTextSchedule.text = formatter.format(calendar.time)
            calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
                calendar.set(year, month, dayOfMonth)
                dateTextSchedule.text = formatter.format(calendar.time)
            }

        }
    }
}