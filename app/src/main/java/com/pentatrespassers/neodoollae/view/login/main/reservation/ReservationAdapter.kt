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

    companion object {
        var reservations = makeDummyReservationData()

        fun appendReservations(new : Reservation){
            reservations.add(new)
        }

        fun editReservations(edit : Reservation){
            for (i in 0..reservations.size-1){
                if(edit.id == reservations[i].id){
                    reservations[i] = edit
                }
            }
        }

        fun deleteReservations(delete : Reservation){
            for (i in 0..reservations.size-1){
                if(delete.id == reservations[i].id){
                    reservations.remove(reservations[i])
                    break
                }
            }
        }

        @JvmName("setReservations1")
        fun setReservations(list : ArrayList<Reservation>){
            reservations = list
        }

        fun makeDummyReservationData(): ArrayList<Reservation> {
            val data: ArrayList<Reservation> = arrayListOf(
                Reservation(1,1,1,"sunny","써니의 방",null,null,null,"샘플 데이터입니다.","샘플 response데이터입니다"),
                Reservation(2,1,1,"sunny","써니의 방",null,null,null,"샘플 데이터입니다.","샘플 response데이터입니다"),
                Reservation(3,1,1,"sunny","써니의 방",null,null,null,"샘플 데이터입니다.","샘플 response데이터입니다"),
                Reservation(4,1,1,"sunny","써니의 방",null,null,null,"샘플 데이터입니다.","샘플 response데이터입니다")


            )


            return data
        }

    }

    inner class CellReservationHolder(private val bind: CellReservationBinding) : RecyclerView.ViewHolder(bind.root) {

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
      //  reservations = reservationList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return reservations.size
    }







}

