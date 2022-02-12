package com.pentatrespassers.neodoollae.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.sql.Timestamp

@Parcelize
data class Reservation(
    var id: Int = UNDEFINED,
    var userId: Int = UNDEFINED,
    var roomId: Int = UNDEFINED,
    var nickname: String = "",
    var hostname: String = "",
    var roomName: String = "",
    var checkIn: Timestamp? = null,
    var checkOut: Timestamp? = null,
    var createdAt: Timestamp? = null,
    var requestMessage: String = "",
    var responseMessage: String = "",
    var status: Int = STATUS_UNDEFINED,
    var type: Int = TYPE_UNDEFINED,
    var member: Int = UNDEFINED
) : Parcelable {
    companion object {
        const val UNDEFINED = -1

        const val STATUS_UNDEFINED = -1
        const val STATUS_WAITING = 0    // 예약 수락 대기중
        const val STATUS_ACCEPTED = 1   // 예약 수락됨
        const val STATUS_DECLINED = 2   // 예약 거절됨

        const val TYPE_UNDEFINED = -1
        const val TYPE_CHECK_IN = 0
        const val TYPE_CHECK_OUT = 1
    }
}
