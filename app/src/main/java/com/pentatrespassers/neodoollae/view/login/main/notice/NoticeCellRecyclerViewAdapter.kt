package com.pentatrespassers.neodoollae.view.login.main.notice

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.databinding.CellNoticeBinding
import com.pentatrespassers.neodoollae.dto.Reservation

class NoticeCellRecyclerViewAdapter(
    private val context: Context, private val reservationList: ArrayList<Reservation>
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class CellNoticeHolder(
        private val bind: CellNoticeBinding
    ) :
        RecyclerView.ViewHolder(bind.root) {

        fun binding(reservation: Reservation) {
            with(bind) {
                reserveInfoTextNotice.text = when (reservation.status) {
                    Reservation.STATUS_WAITING -> context.getString(R.string.reservation_waiting)
                    Reservation.STATUS_ACCEPTED -> context.getString(R.string.reservation_accepted)
                    Reservation.STATUS_DECLINED -> context.getString(R.string.reservation_declined)
                    else -> ""
                }
                friendNameTextNotice.text = reservation.nickname

                cellLayoutNotice.setOnClickListener {
                    if (expandedCellLayoutNotice.visibility == View.VISIBLE) {
                        expandedCellLayoutNotice.visibility = View.GONE
                        arrowImageNotice.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24)
                    } else {
                        expandedCellLayoutNotice.visibility = View.VISIBLE
                        arrowImageNotice.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
                    }
                }
            }


        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        return CellNoticeHolder(CellNoticeBinding.inflate(layoutInflater, parent, false))
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val reservation = reservationList[position]
        (holder as CellNoticeHolder).binding(reservation)
    }

    override fun getItemCount(): Int {
        return reservationList.size
    }


}