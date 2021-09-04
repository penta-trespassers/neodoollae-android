package com.pentatrespassers.neodoollae.view.login.main.notification

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.databinding.CellNotificationBinding
import com.pentatrespassers.neodoollae.dto.Notification

class NotificationAdapter(
    private val context: Context, private val notificationList: List<Notification>
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    inner class CellNotificationHolder(
        private val bind: CellNotificationBinding
    ) :
        RecyclerView.ViewHolder(bind.root) {

        fun binding(notification: Notification) {
            with(bind) {
                notificationTimeTextNotice.text = notification.time

                when (notification.status) {
                    Notification.STATUS_RESERVE_WAITING -> {
                        notificationTextNotice.text =
                            context.getString(R.string.reservation_waiting, notification.nickname)
                        notificationImageNotice.setImageResource(R.drawable.ic_watch_later_24dp)
                    }
                    Notification.STATUS_RESERVE_ACCEPTED -> {
                        notificationTextNotice.text =
                            context.getString(R.string.reservation_accepted, notification.nickname)
                        notificationImageNotice.setImageResource(R.drawable.ic_check_circle_24dp)
                    }
                    Notification.STATUS_RESERVE_DECLINED -> {
                        notificationTextNotice.text =
                            context.getString(R.string.reservation_declined, notification.nickname)
                        notificationImageNotice.setImageResource(R.drawable.ic_cancel_24dp)
                    }
                    Notification.STATUS_REVIEW_HOST -> {
                        notificationTextNotice.text =
                            context.getString(R.string.review_host, notification.nickname)
                        notificationImageNotice.setImageResource(R.drawable.ic_edit_24dp)
                    }
                    Notification.STATUS_REVIEW_GUEST -> {
                        notificationTextNotice.text =
                            context.getString(R.string.review_guest, notification.nickname)
                        notificationImageNotice.setImageResource(R.drawable.ic_edit_24dp)
                    }
                    Notification.STATUS_REVIEW_ROOM -> {
                        notificationTextNotice.text =
                            context.getString(R.string.review_room, notification.nickname)
                        notificationImageNotice.setImageResource(R.drawable.ic_edit_24dp)
                    }
                }

                constraintLayoutNotice.setOnClickListener {
                    if (notification.status == 0) {
                        if (reserveExpandedGroupNotice.visibility == View.VISIBLE) {
                            reserveExpandedGroupNotice.visibility = View.GONE
                            reserveUndecidedGroupNotice.visibility = View.GONE
                            arrowImageNotice.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
                        } else {
                            reserveExpandedGroupNotice.visibility = View.VISIBLE
                            reserveUndecidedGroupNotice.visibility = View.VISIBLE
                            arrowImageNotice.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24)
                        }
                    } else if (notification.status < 3) {
                        if (reserveExpandedGroupNotice.visibility == View.VISIBLE) {
                            reserveExpandedGroupNotice.visibility = View.GONE
                            View.GONE.also { reserveDecidedGroupNotice.visibility = it }
                            arrowImageNotice.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
                        } else {
                            reserveExpandedGroupNotice.visibility = View.VISIBLE
                            reserveDecidedGroupNotice.visibility = View.VISIBLE
                            arrowImageNotice.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24)
                        }
                    } else {
                        if (reviewExpandedGroupNotice.visibility == View.VISIBLE) {
                            reviewExpandedGroupNotice.visibility = View.GONE
                            arrowImageNotice.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
                        } else {
                            reviewExpandedGroupNotice.visibility = View.VISIBLE
                            arrowImageNotice.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24)
                        }
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        
        return CellNotificationHolder(CellNotificationBinding.inflate(layoutInflater, parent, false))
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val reservation = notificationList[position]
        (holder as CellNotificationHolder).binding(reservation)
    }

    override fun getItemCount(): Int {
        return notificationList.size
    }


}