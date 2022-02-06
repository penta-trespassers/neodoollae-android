package com.pentatrespassers.neodoollae.view.login.main.reservation

import android.R
import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.pentatrespassers.neodoollae.databinding.CellFriendListBinding
import com.pentatrespassers.neodoollae.databinding.CellReservationBinding
import com.pentatrespassers.neodoollae.dto.Reservation
import com.pentatrespassers.neodoollae.dto.User
import com.pentatrespassers.neodoollae.lib.Util
import com.pentatrespassers.neodoollae.view.login.main.friend.friendlist.FriendListAdapter
import com.pentatrespassers.neodoollae.view.login.main.friend.friendlist.FriendProfileActivity
import splitties.activities.start
import splitties.bundle.putExtras


class ReservationAdapter(
    private val context: Context,
    var reservationList: ArrayList<Reservation>
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    private var reservations : ArrayList<Reservation> = reservationList


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
        val data = reservations[position]
        (holder as ReservationAdapter.CellReservationHolder).binding(data)
    }

    fun refresh(reservationList: ArrayList<Reservation>) {
        this.reservations = reservationList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return reservations.size
    }





}