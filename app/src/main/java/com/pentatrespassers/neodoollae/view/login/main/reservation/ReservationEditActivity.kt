package com.pentatrespassers.neodoollae.view.login.main.reservation

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.CalendarView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.pentatrespassers.neodoollae.databinding.ActivityReservationEditBinding
import com.pentatrespassers.neodoollae.dto.Reservation
import com.pentatrespassers.neodoollae.lib.Util.hide
import com.pentatrespassers.neodoollae.view.login.main.ReservationFragment
import splitties.toast.toast

class ReservationEditActivity : AppCompatActivity() {
    var reservation : Reservation = Reservation()

    var isDateExpanded : Boolean = false
    var isTimeExpanded : Boolean = false

    var num : Int = 0



    private val bind by lazy {
        ActivityReservationEditBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(bind) {
            setContentView(root)

            var intent : Intent = getIntent()
            reservation = intent.getParcelableExtra("Reservation")!!

            reservationRoomNameText.text = reservation.roomName
            reservationVisitorNameText.text = reservation.nickname

            reservationVisitNumberText.text ="$num"


            settingDateAndTimeConstraintLayout.setOnClickListener {
                 toggleLayout(!isDateExpanded, it, layoutExpandDate)
                isDateExpanded = true
            }

            reservationCalendarView.setOnDateChangeListener { calendarView, i, i2, i3 ->
               toggleLayout(!isTimeExpanded, calendarView, layoutExpandTime)
                isTimeExpanded = true
            }

            SetTimeEndbutton.setOnClickListener{
                toggleLayout(!isDateExpanded, it, layoutExpandDate)
                toggleLayout(!isTimeExpanded, it, layoutExpandTime)
                isDateExpanded = false
                isTimeExpanded = false

            }


            reservationAddButton.setOnClickListener {
                toast("예약이 완료되었습니다.")
             //   var intent = Intent(this@ReservationEditActivity,ReservationFragment)
               // startActivity(intent)
                finish()

            }

            visitNumPlusButton.setOnClickListener {
                num++
               reservationVisitNumberText.text = "$num"
            }

            visitNumMinusButton.setOnClickListener {
               num--
               reservationVisitNumberText.text = "$num"
            }

            editToReservationListButton.setOnClickListener {
                finish()
            }

        }

    }

    private fun toggleLayout(isExpanded: Boolean, view: View, layoutExpand: LinearLayout) {

        ToggleAnimation.toggleArrow(view, isExpanded)
        if (isExpanded) {
            ToggleAnimation.expand(layoutExpand)
        }else{
            ToggleAnimation.collapse(layoutExpand)
        }
    }
}