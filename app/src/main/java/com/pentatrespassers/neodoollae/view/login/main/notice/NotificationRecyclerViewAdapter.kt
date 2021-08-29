package com.pentatrespassers.neodoollae.view.login.main.notice

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.databinding.CellNotificationBinding
import com.pentatrespassers.neodoollae.dto.Notification

class NotificationRecyclerViewAdapter(
    private val context: Context, private val notificationList: ArrayList<Notification>
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class CellNoticeHolder(
        private val bind: CellNotificationBinding
    ) :
        RecyclerView.ViewHolder(bind.root) {

        fun binding(notification: Notification) {
            with(bind) {
                setNotification(notification)

                constraintLayoutNotice.setOnClickListener {
                    if (notification.status == 0) {
                        if (reserveExpandedGroupNotice.visibility == View.VISIBLE) {
                            reserveExpandedGroupNotice.visibility = View.GONE
                            reserveUndecidedGroupNotice.visibility = View.GONE
                        arrowImageNotice.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24)
                        } else {
                            reserveExpandedGroupNotice.visibility = View.VISIBLE
                            reserveUndecidedGroupNotice.visibility = View.VISIBLE
                            arrowImageNotice.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
                        }
                    } else if (notification.status < 3){
                        if (reserveExpandedGroupNotice.visibility == View.VISIBLE) {
                            reserveExpandedGroupNotice.visibility = View.GONE
                            View.GONE.also { reserveDecidedGroupNotice.visibility = it }
                            arrowImageNotice.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24)
                        } else {
                            reserveExpandedGroupNotice.visibility = View.VISIBLE
                            reserveDecidedGroupNotice.visibility = View.VISIBLE
                            arrowImageNotice.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
                        }
                    } else {
                        if (reviewExpandedGroupNotice.visibility == View.VISIBLE) {
                            reviewExpandedGroupNotice.visibility = View.GONE
                            arrowImageNotice.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24)
                        } else {
                            reviewExpandedGroupNotice.visibility = View.VISIBLE
                            arrowImageNotice.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
                        }
                    }
                }
            }
        }


        private fun setNotification(notification: Notification) {
            with(bind) {
                notificationTimeTextNotice.text = notification.time

                when (notification.status) {
                    Notification.STATUS_RESERVE_WAITING -> {
                        notificationTextNotice.text =
                            notification.nickname + context.getString(R.string.reservation_waiting)
                        notificationImageNotice.setImageResource(R.drawable.ic_watch_later_24dp)
                    }
                    Notification.STATUS_RESERVE_ACCEPTED -> {
                        notificationTextNotice.text =
                            notification.nickname + context.getString(R.string.reservation_accepted)
                        notificationImageNotice.setImageResource(R.drawable.ic_check_circle_24dp)
                    }
                    Notification.STATUS_RESERVE_DECLINED -> {
                        notificationTextNotice.text =
                            notification.nickname + context.getString(R.string.reservation_declined)
                        notificationImageNotice.setImageResource(R.drawable.ic_cancel_24dp)
                    }
                    Notification.STATUS_REVIEW_HOST -> {
                        notificationTextNotice.text =
                            notification.nickname + context.getString(R.string.review_host)
                        notificationImageNotice.setImageResource(R.drawable.ic_edit_24dp)
                    }
                    Notification.STATUS_REVIEW_GUEST -> {
                        notificationTextNotice.text =
                            notification.nickname + context.getString(R.string.review_guest)
                        notificationImageNotice.setImageResource(R.drawable.ic_edit_24dp)
                    }
                    Notification.STATUS_REVIEW_ROOM -> {
                        notificationTextNotice.text =
                            notification.nickname + context.getString(R.string.review_room)
                        notificationImageNotice.setImageResource(R.drawable.ic_edit_24dp)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        return CellNoticeHolder(CellNotificationBinding.inflate(layoutInflater, parent, false))
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val reservation = notificationList[position]
        (holder as CellNoticeHolder).binding(reservation)
    }

    override fun getItemCount(): Int {
        return notificationList.size
    }


}