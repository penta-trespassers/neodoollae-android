package com.pentatrespassers.neodoollae.view.login.main.reservation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.databinding.CellReservationBinding
import com.pentatrespassers.neodoollae.dto.Reservation

class ReservationAdapter(
    private val context: Context, private val reservationList: ArrayList<Reservation>
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    inner class CellNotificationReservationHolder(
        private val bind: CellReservationBinding
    ) :
        RecyclerView.ViewHolder(bind.root) {

        fun binding(reservation: Reservation) {
            with(bind) {


                when (reservation.status) {
                    Reservation.STATUS_WAITING -> {
                        reservationMessageText.text =
                            context.getString(R.string.reservation_waiting, reservation.nickname)
                        statusImage.setImageResource(R.drawable.ic_watch_later_24dp)
                    }
                    Reservation.STATUS_ACCEPTED -> {
                        reservationMessageText.text =
                            context.getString(R.string.reservation_accepted, reservation.nickname)
                        statusImage.setImageResource(R.drawable.ic_check_circle_24dp)
                    }
                    Reservation.STATUS_DECLINED -> {
                        reservationMessageText.text =
                            context.getString(R.string.reservation_declined, reservation.nickname)
                        statusImage.setImageResource(R.drawable.ic_cancel_24dp)
                    }
                    Reservation.STATUS_UNDEFINED -> {
                        itemView.visibility = View.GONE
                    }
                }
                reservationDateText.text = "${reservation.createdAt}"
                roomNameTextReservation.text = reservation.roomName

                when (reservation.status) {
                    Reservation.STATUS_WAITING -> {
                        itemView.setOnClickListener {
                            if (reservationDetailConstraint.visibility == View.VISIBLE) {
                                reservationDetailConstraint.visibility = View.GONE
                                reserveUndecidedGroupReservation.visibility = View.GONE
                                arrowImageReservation.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
                            } else {
                                reservationDetailConstraint.visibility = View.VISIBLE
                                reserveUndecidedGroupReservation.visibility = View.VISIBLE
                                arrowImageReservation.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24)
                            }
                        }

                    }
                    Reservation.STATUS_DECLINED, Reservation.STATUS_ACCEPTED -> {
                        itemView.setOnClickListener {
                            if (reservationDetailConstraint.visibility == View.VISIBLE) {
                                reservationDetailConstraint.visibility = View.GONE
                                reserveDecidedGroupReservation.visibility = View.GONE
                                arrowImageReservation.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
                            } else {
                                reservationDetailConstraint.visibility = View.VISIBLE
                                reserveDecidedGroupReservation.visibility = View.VISIBLE
                                arrowImageReservation.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24)
                            }
                        }
                    }
                }


            }
        }
    }


    override fun getItemViewType(position: Int): Int {
        return reservationList[position].status
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