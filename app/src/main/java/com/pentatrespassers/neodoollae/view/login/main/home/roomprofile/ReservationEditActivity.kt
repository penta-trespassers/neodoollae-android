package com.pentatrespassers.neodoollae.view.login.main.home.roomprofile

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CalendarView.OnDateChangeListener
import androidx.appcompat.app.AppCompatActivity
import com.pentatrespassers.neodoollae.databinding.ActivityReservationEditBinding
import com.pentatrespassers.neodoollae.dto.Reservation
import com.pentatrespassers.neodoollae.lib.Util
import com.pentatrespassers.neodoollae.view.login.main.reservation.ToggleAnimation
import splitties.bundle.BundleSpec
import splitties.bundle.bundle
import splitties.bundle.withExtras
import splitties.toast.toast
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*


class ReservationEditActivity : AppCompatActivity() {

    var isDateExpanded: Boolean = false
    var isTimeExpanded: Boolean = false

    var isStartDateSet = false

    var isCreate = false

    var toastWord = "수정"

    val calendar = Calendar.getInstance()
    val formatter = Util.getDateFormatter("yy MM dd EEEE")



    object Extras : BundleSpec() {
        var reservation: Reservation by bundle()
    }

    private val reservation by lazy {
        withExtras(Extras) {
            reservation
        }
    }

    fun timeOnClick(button: View){
        var time : String = ""
        var timestamp : Timestamp? = null
        time = (button as Button).text as String

        (button as Button).text.split(":").let{
            calendar.set(Calendar.HOUR_OF_DAY, it[0].toInt())
            calendar.set(Calendar.MINUTE, it[1].toInt())
            timestamp = Timestamp.from(calendar.toInstant()) as Timestamp?

        }

        with(bind){
            if(isStartDateSet == false) {
                visitStartTimeText.text = time
                reservation.checkIn = timestamp
            }
            else{
                visitEndDateText.text = time
                reservation.checkOut = timestamp
            }
        }

    }


    private val bind by lazy {
        ActivityReservationEditBinding.inflate(layoutInflater)
    }

    fun pmOnClick(button: View){
        var time : String = ""
        time = (button as Button).text as String
        with(bind){
            if(isStartDateSet == false)
                visitStartTimeText.text = time
            else{
                visitEndDateText.text = time
            }
        }
    }
    fun amOnClick(button : View){
        var time : String = ""
        time = (button as Button).text as String
        with(bind){
            if(isStartDateSet == true)
                visitStartTimeText.text = time
            else{
                visitEndDateText.text = time
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(bind) {
            setContentView(root)

            if (reservation == null) {
                isCreate = true
                toastWord = "예약이"
            } else {
                isCreate = false
                toastWord = "수정이"
            }

            reservationRoomNameText.text = reservation.roomName
            reservationVisitorNameText.text = reservation.nickname
            vistStartDatetext.text = reservation.checkIn.toString()
            visitStartTimeText.text = reservation.checkIn.toString()
            visitEndDateText.text = reservation.checkOut.toString()
            visitEndTimeText.text = reservation.checkOut.toString()

            reservationVisitNumberText.text = reservation.member.toString()

            toHostEditText.setText(reservation.requestMessage)

            reservationCalendarView.setClickable(true)

            reservationAddButton.text = when (isCreate) {
                true -> "예약하기"
                false -> "수정하기"
            }

            settingDateAndTimeConstraintLayout.setOnClickListener {
//                toggleLayout(!isDateExpanded, it, layoutExpandDate)
            }


            reservationCalendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
                calendar.set(year, month, dayOfMonth)

              //  toggleLayout(!isTimeExpanded, view, layoutExpandTime)
                isTimeExpanded = true

                if (isStartDateSet == false) {
                    vistStartDatetext.text = formatter.format(calendar.time)

                } else {
                    visitEndDateText.text = formatter.format(calendar.time)

                }
            }



            reservationSetTimeButton.setOnClickListener {
//                toggleLayout(!isDateExpanded, it, layoutExpandDate)
               /// toggleLayout(!isTimeExpanded, it, layoutExpandTime)
                isDateExpanded = false
                isTimeExpanded = false

            }


            reservationAddButton.setOnClickListener {
                toast("${toastWord} 완료되었습니다.")
                //  reservation.checkIn =
                // reservation.checkOut =
                reservation.member = (reservationVisitNumberText.text as String).toInt()
                reservation.requestMessage = toHostEditText.text.toString()
//                editReservations(reservation)
                print(reservation.member)
                finish()

            }

            visitNumPlusButton.setOnClickListener {
                reservation.member++
                reservationVisitNumberText.text = "${reservation.member}"
            }

            visitNumMinusButton.setOnClickListener {
                reservation.member++
                reservationVisitNumberText.text = "${reservation.member}"
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
        } else {
            ToggleAnimation.collapse(layoutExpand)
        }
    }
}