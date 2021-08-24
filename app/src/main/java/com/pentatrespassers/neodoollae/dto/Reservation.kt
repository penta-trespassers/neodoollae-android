package com.pentatrespassers.neodoollae.dto

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
