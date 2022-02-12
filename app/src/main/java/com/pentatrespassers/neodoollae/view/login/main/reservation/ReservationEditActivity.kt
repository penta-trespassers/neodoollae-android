package com.pentatrespassers.neodoollae.view.login.main.reservation

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CalendarView.OnDateChangeListener
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.databinding.ActivityReservationEditBinding
import com.pentatrespassers.neodoollae.dto.Reservation
import splitties.bundle.BundleSpec
import splitties.bundle.bundle
import splitties.bundle.withExtras
import com.pentatrespassers.neodoollae.view.login.main.reservation.ReservationAdapter.Companion.editReservations
import com.pentatrespassers.neodoollae.view.login.main.reservation.ReservationAdapter.Companion.reservations
import splitties.toast.toast
import java.text.SimpleDateFormat
import java.util.*


class ReservationEditActivity : AppCompatActivity() {

    var isDateExpanded : Boolean = false
    var isTimeExpanded : Boolean = false

    var isStartDateSet = false

    var isCreate = false

    var toastWord = "수정"


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

            var intent : Intent = getIntent()
            reservation = intent.getParcelableExtra("Reservation")!!
            if(reservation == null){
                isCreate = true
                toastWord = "예약이"
            }else{
                isCreate = false
                toastWord = "수정이"
            }

            reservationRoomNameText.text = reservation.roomName
            reservationVisitorNameText.text = reservation.nickname
            vistStartDatetext.text = reservation.checkIn.toString()
            visitStartTimeText.text = reservation.checkIn.toString()
            visitEndDateText.text = reservation.checkOut.toString()
            visitEndTimeText.text = reservation.checkOut.toString()

            reservationVisitNumberText.text =reservation.member.toString()

            toHostEditText.setText(reservation.requestMessage)

            reservationCalendarView.setClickable(true)

            reservationAddButton.text = when (isCreate) {
                true -> "예약하기"
                false -> "수정하기"
            }

            settingDateAndTimeConstraintLayout.setOnClickListener {
                 isDateExpanded = toggleLayout(!isDateExpanded, it, layoutExpandDate)

                isTimeExpanded = true
            }

            reservationCalendarView.setOnDateChangeListener(OnDateChangeListener { view, year, month, dayOfMonth ->
                val month = month + 1
                val calendar = Calendar.getInstance()
                calendar.set(year, month, dayOfMonth)
                val date = calendar.time
                val simpledateformat = SimpleDateFormat("EEEE", Locale.getDefault())
                val dayName: String = simpledateformat.format(date)

                toggleLayout(!isTimeExpanded, view, layoutExpandTime)
                isTimeExpanded = true

                if(isStartDateSet == false) {
                    vistStartDatetext.text = "$year.$month.$dayOfMonth.$dayName"
                }
                else{
                    visitEndDateText.text = "$year.$month.$dayOfMonth.$dayName"

                }
            })

            SetTimeEndbutton.setOnClickListener{
                toggleLayout(!isDateExpanded, it, layoutExpandDate)
                toggleLayout(!isTimeExpanded, it, layoutExpandTime)
                isDateExpanded = false
                isTimeExpanded = false

            }


            reservationAddButton.setOnClickListener {
                toast("${toastWord} 완료되었습니다.")
              //  reservation.checkIn =
                // reservation.checkOut =
                reservation.member = (reservationVisitNumberText.text as String).toInt()
                reservation.requestMessage = toHostEditText.text.toString()
                editReservations(reservation)
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
        }else{
            ToggleAnimation.collapse(layoutExpand)
        }
    }
}