package com.pentatrespassers.neodoollae.view.login.main.reservation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pentatrespassers.neodoollae.databinding.ActivityReservationProfileBinding
import com.pentatrespassers.neodoollae.dto.Reservation
import com.pentatrespassers.neodoollae.lib.Util.show
import com.pentatrespassers.neodoollae.view.login.main.home.roomprofile.ReservationEditActivity
import splitties.activities.start
import splitties.bundle.BundleSpec
import splitties.bundle.bundle
import splitties.bundle.putExtras
import splitties.bundle.withExtras
import splitties.toast.toast

class ReservationProfileActivity : AppCompatActivity() {

    object Extras : BundleSpec() {
        var reservation: Reservation by bundle()
    }

    private val reservation by lazy {
        withExtras(Extras) {
            reservation
        }
    }

    private val bind by lazy {
        ActivityReservationProfileBinding.inflate(layoutInflater)
    }

    var isWait = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(bind) {
            setContentView(root)

            reservationRoomNameText.text = reservation.roomName
            reservationVisitorNameText.text = reservation.nickname
            vistStartDatetext.text = reservation.checkIn.toString()
            vistStartTimeText.text = reservation.checkIn.toString()
            visitEndDateText.text = reservation.checkOut.toString()
            visitEndTimeText.text = reservation.checkOut.toString()
            visitNumberText.text = reservation.member.toString()
            toHostText.text = reservation.requestMessage

            if (isWait == true) {
                buttonsConstraint.show()

            }


            reservationProfileEditButton.setOnClickListener {
                start<ReservationEditActivity> {
                    putExtras(ReservationEditActivity.Extras) {
                        reservation = this@ReservationProfileActivity.reservation
                    }
                }
            }

            reservationProfileDeleteButton.setOnClickListener {
                toast("예약이 삭제되었습니다.")
                finish()
            }

            backProfileToReservationListButton.setOnClickListener {
                finish()
            }


        }

    }
}