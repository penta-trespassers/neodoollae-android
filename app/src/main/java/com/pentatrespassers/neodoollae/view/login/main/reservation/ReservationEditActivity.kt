package com.pentatrespassers.neodoollae.view.login.main.reservation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.pentatrespassers.neodoollae.databinding.ActivityReservationEditBinding
import com.pentatrespassers.neodoollae.dto.Reservation
import splitties.bundle.BundleSpec
import splitties.bundle.bundle
import splitties.bundle.withExtras

class ReservationEditActivity : AppCompatActivity() {

    var isDateExpanded : Boolean = false
    var isTimeExpanded : Boolean = false

    var num : Int = 0

    object Extras : BundleSpec() {
        var reservation: Reservation by bundle()
    }

    private val reservation by lazy {
        withExtras(Extras) {
            reservation
        }
    }



    private val bind by lazy {
        ActivityReservationEditBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(bind) {
            setContentView(root)

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
                toggleLayout(!isDateExpanded, it, layoutExpandDate)
                isDateExpanded = true
//                toast("예약이 완료되었습니다.")
             //   var intent = Intent(this@ReservationEditActivity,ReservationFragment)
               // startActivity(intent)
//                finish()

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

    private fun toggleLayout(isExpanded: Boolean, view: View, layoutExpand: View) {

        ToggleAnimation.toggleArrow(view, isExpanded)
        if (isExpanded) {
            ToggleAnimation.expand(layoutExpand)
        }else{
            ToggleAnimation.collapse(layoutExpand)
        }
    }
}