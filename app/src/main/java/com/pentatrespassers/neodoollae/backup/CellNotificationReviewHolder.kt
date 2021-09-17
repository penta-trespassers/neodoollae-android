package com.pentatrespassers.neodoollae.backup

//inner class CellNotificationReviewHolder(
//    private val bind: CellNotificationReviewBinding
//) :
//    RecyclerView.ViewHolder(bind.root) {
//
//    fun binding(notification: Notification) {
//        with(bind) {
//            notificationTimeTextNotice.text = notification.time
//
//            when (notification.status) {
//                Notification.STATUS_REVIEW_HOST -> {
//                    notificationTextNotice.text =
//                        context.getString(R.string.review_host, notification.nickname)
//                    notificationImageNotice.setImageResource(R.drawable.ic_edit_24dp)
//                }
//                Notification.STATUS_REVIEW_GUEST -> {
//                    notificationTextNotice.text =
//                        context.getString(R.string.review_guest, notification.nickname)
//                    notificationImageNotice.setImageResource(R.drawable.ic_edit_24dp)
//                }
//                Notification.STATUS_REVIEW_ROOM -> {
//                    notificationTextNotice.text =
//                        context.getString(R.string.review_room, notification.nickname)
//                    notificationImageNotice.setImageResource(R.drawable.ic_edit_24dp)
//                }
//            }
//
//            constraintLayoutNotice.setOnClickListener {
//                if (reviewExpandedGroupNotice.visibility == View.VISIBLE) {
//                    reviewExpandedGroupNotice.visibility = View.GONE
//                    arrowImageNotice.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
//                } else {
//                    reviewExpandedGroupNotice.visibility = View.VISIBLE
//                    arrowImageNotice.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24)
//                }
//            }
//        }
//    }
//}