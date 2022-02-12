package com.pentatrespassers.neodoollae.view.login.main.reservation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pentatrespassers.neodoollae.databinding.CellReservationBinding
import com.pentatrespassers.neodoollae.dto.Reservation
import splitties.activities.start
import splitties.bundle.putExtras


class ReservationAdapter(
    private val context: Context
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    var reservationList : ArrayList<Reservation> = arrayListOf()


    inner class CellReservationHolder(
        private val bind: CellReservationBinding
    ) :
        RecyclerView.ViewHolder(bind.root) {
        fun binding(reservation: Reservation) {
            with(bind) {

               // reservationStartDateText.text = Util.simpleDateFormatter.format(reservation.checkIn?.time)
                //reservationEndDateText.text = Util.simpleDateFormatter.format(reservation.checkOut?.time)
                reservationEndTimeText.text = "오전 YY:MM"
                reservationStartTimeText.text = "오후 YY:MM"

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
                    context.start<ReservationProfileActivity> {
                        putExtras(ReservationProfileActivity.Extras) {
                            this.reservation = reservation
                        }
                    }

                }



            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CellReservationHolder(
            CellReservationBinding.inflate(layoutInflater, parent, false)
        )
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
      //  (holder as CellNotificationReservationHolder).binding(reservations[position])
        val data = reservationList[position]
        (holder as ReservationAdapter.CellReservationHolder).binding(data)
    }

    fun refresh(reservationList: ArrayList<Reservation>) {
        this.reservationList = reservationList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return reservationList.size
    }





}