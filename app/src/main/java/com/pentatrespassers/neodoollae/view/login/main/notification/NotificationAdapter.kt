package com.pentatrespassers.neodoollae.view.login.main.notification

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.databinding.CellNotificationBinding
import com.pentatrespassers.neodoollae.dto.Notification
import com.pentatrespassers.neodoollae.dto.Notification.Companion.TYPE_FRIEND_ACCEPTED
import com.pentatrespassers.neodoollae.dto.Notification.Companion.TYPE_FRIEND_DECLINED
import com.pentatrespassers.neodoollae.dto.Notification.Companion.TYPE_FRIEND_REQUEST
import com.pentatrespassers.neodoollae.dto.Notification.Companion.TYPE_RESERVE_ACCEPTED
import com.pentatrespassers.neodoollae.dto.Notification.Companion.TYPE_RESERVE_DECLINED
import com.pentatrespassers.neodoollae.dto.Notification.Companion.TYPE_RESERVE_WAITING
import com.pentatrespassers.neodoollae.dto.Notification.Companion.TYPE_REVIEW_GUEST
import com.pentatrespassers.neodoollae.dto.Notification.Companion.TYPE_REVIEW_HOST
import com.pentatrespassers.neodoollae.lib.Util
import java.text.SimpleDateFormat

class NotificationAdapter(
    private val context: Context,
    private val notificationList: ArrayList<Notification>,
    private var notificationSelectedList: ArrayList<Notification> = notificationList,
    private val formatter: SimpleDateFormat = Util.getDateFormatter("yyyy.MM.dd.EEEE")
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class NotificationHolder(private val bind: CellNotificationBinding) :
        RecyclerView.ViewHolder(bind.root) {
        fun binding(notification: Notification) {
            with(bind) {
                notificationMainText.text = notification.title
                notificationDateText.text = formatter.format(notification.createdAt)

                when (notification.type) {
                    TYPE_RESERVE_WAITING, TYPE_FRIEND_REQUEST -> {
                        notificationStatusImage.setImageResource(R.drawable.ic_common_notification_wait)
                    }
                    TYPE_RESERVE_ACCEPTED, TYPE_FRIEND_ACCEPTED -> {
                        notificationStatusImage.setImageResource(R.drawable.ic_common_notification_check)
                    }
                    TYPE_RESERVE_DECLINED, TYPE_FRIEND_DECLINED -> {
                        notificationStatusImage.setImageResource(R.drawable.ic_common_notification_cancel)
                    }
                    TYPE_REVIEW_HOST, TYPE_REVIEW_GUEST -> {
                        notificationStatusImage.setImageResource(R.drawable.ic_common_notification_review)

                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        return NotificationHolder(CellNotificationBinding.inflate(layoutInflater, parent, false))
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as NotificationHolder).binding(notificationSelectedList[position])
    }

    override fun getItemCount(): Int {
        return notificationSelectedList.size
    }

    fun selectType(mode: Int): NotificationAdapter {
        notificationSelectedList = ArrayList()
        for (notification in notificationList) {
            when (mode) {
                1 -> {
                    if (notification.type == 0 ||
                        notification.type == 1 ||
                        notification.type == 2
                    ) notificationSelectedList.add(notification)
                }
                2 -> {
                    if (notification.type == 3 ||
                        notification.type == 4
                    ) notificationSelectedList.add(notification)
                }
                3 -> {
                    if (notification.type == 5 ||
                        notification.type == 6 ||
                        notification.type == TYPE_FRIEND_DECLINED
                    ) notificationSelectedList.add(notification)
                }
                else -> {
                    notificationSelectedList = notificationList
                }
            }
        }
        return this
    }
}