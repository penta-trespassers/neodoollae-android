package com.pentatrespassers.neodoollae.view.login.main.reservation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pentatrespassers.neodoollae.databinding.ActivityReservationProfileBinding
import com.pentatrespassers.neodoollae.dto.Reservation
import splitties.activities.start
import splitties.bundle.BundleSpec
import splitties.bundle.bundle
import splitties.bundle.putExtras
import splitties.bundle.withExtras

class ReservationProfileActivity: AppCompatActivity() {

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(bind) {
            setContentView(root)

            reservationRoomNameText.text = reservation.roomName
            reservationVisitorNameText.text = reservation.nickname


            reservationProfileEditButton.setOnClickListener{
                start<ReservationEditActivity> {
                    putExtras(ReservationEditActivity.Extras) {
                        reservation = this@ReservationProfileActivity.reservation
                    }
                }
            }


            backProfileToReservationListButton.setOnClickListener {
                finish()
            }




        }

    }
}