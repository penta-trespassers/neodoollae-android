package com.pentatrespassers.neodoollae.dto

import java.sql.Timestamp

data class Reservation(
    var id: Int = -1,
    var userId: Int = -1,
    var roomId: Int = -1,
    var nickname: String = "",
    var roomName: String = "",
    var checkIn: Timestamp? = null,
    var checkOut: Timestamp? = null,
    var createdAt: Timestamp? = null,
    var requestMessage: String = "",
    var responseMessage: String = "",
    var status: Int = STATUS_UNDEFINED
) {
    companion object {
        const val STATUS_UNDEFINED = -1
        const val STATUS_WAITING = 0    // 예약 수락 대기중
        const val STATUS_ACCEPTED = 1   // 예약 수락됨
        const val STATUS_DECLINED = 2   // 예약 거절됨
    }
}
