package com.pentatrespassers.neodoollae.dto

data class Notification(
    var nickname: String = "",
    var time: String = "",
    var status: Int = STATUS_UNDEFINED
) {
    companion object {
        const val STATUS_UNDEFINED = -1
        const val STATUS_RESERVE_WAITING = 0
        const val STATUS_RESERVE_ACCEPTED = 1
        const val STATUS_RESERVE_DECLINED = 2
        const val STATUS_REVIEW_HOST = 3
        const val STATUS_REVIEW_GUEST = 4
        const val STATUS_REVIEW_ROOM = 5
    }
}


/*

data class Reservation(
    var nickname: String = "",
    var checkIn: Long = 0,
    var checkOut: Long = 0,
    var requestMessage: String = "",
    var responseMessage: String = "",
    var status: Int = STATUS_UNDEFINED
) {
    companion object {
        const val STATUS_UNDEFINED = -1
        const val STATUS_WAITING = 0
        const val STATUS_ACCEPTED = 1
        const val STATUS_DECLINED = 2
    }
}

 */