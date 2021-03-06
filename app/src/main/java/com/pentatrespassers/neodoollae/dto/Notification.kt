package com.pentatrespassers.neodoollae.dto

data class Notification(
    var title: String = "",
    var message: String = "",
    var createdAt: Long = 0,
    var type: Int = TYPE_UNDEFINED
) {
    companion object {
        const val TYPE_UNDEFINED = -1
        const val TYPE_RESERVE_WAITING = 0
        const val TYPE_RESERVE_ACCEPTED = 1
        const val TYPE_RESERVE_DECLINED = 2
        const val TYPE_REVIEW_HOST = 3
        const val TYPE_REVIEW_GUEST = 4
        const val TYPE_FRIEND_REQUEST = 5
        const val TYPE_FRIEND_ACCEPTED = 6
        const val TYPE_FRIEND_DECLINED = 7
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