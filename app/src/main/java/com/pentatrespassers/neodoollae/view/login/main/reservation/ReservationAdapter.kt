package com.pentatrespassers.neodoollae.view.login.main.reservation

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.databinding.CellReservationBinding
import com.pentatrespassers.neodoollae.dto.Reservation
import com.pentatrespassers.neodoollae.lib.Util
import java.util.*

class ReservationAdapter(
    private val context: Context, var reservationList: ArrayList<Reservation>
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    inner class CellNotificationReservationHolder(
        private val bind: CellReservationBinding
    ) :
        RecyclerView.ViewHolder(bind.root) {

        fun binding(reservation: Reservation) {
            with(bind) {


                reservationStartDateText.text = Util.simpleDateFormatter.format(reservation.createdIn?.time)
                reservationEndDateText.text = Util.simpleDateFormatter.format(reservation.checkOut?.time)

                when (reservation.status) {
                    Reservation.STATUS_WAITING -> {
                        itemView.setOnClickListener {
                            if (reservationDetailConstraint.visibility == View.VISIBLE) {
                                reservationDetailConstraint.visibility = View.GONE
                                reserveUndecidedGroupReservation.visibility = View.GONE

                            } else {
                                reservationDetailConstraint.visibility = View.VISIBLE
                                reserveUndecidedGroupReservation.visibility = View.VISIBLE
                            }
                        }

                    }


                }

                itemView.setOnClickListener {

                }


            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CellNotificationReservationHolder(
            CellReservationBinding.inflate(layoutInflater, parent, false)
        )
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CellNotificationReservationHolder).binding(reservationList[position])

    }

    override fun getItemCount(): Int {
        return reservationList.size
    }


}