package com.pentatrespassers.neodoollae.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Room(
    var id: Int? = null,
    var userId: Int? = null,
    var nickname: String = "",
    var roomName: String = "",
    var address: String = "",
    var roomImages: List<String>? = null,
    var detailAddress: String = "",
    var description: String = "",
    var latitude: Double = COORDINATE_UNDEFINED,
    var longitude: Double = COORDINATE_UNDEFINED,
    var status: Int = STATUS_UNDEFINED
) : Parcelable {
    companion object {
        const val COORDINATE_UNDEFINED = 324.0
        const val STATUS_UNDEFINED = -1
        const val STATUS_OPEN = 0
        const val STATUS_RESTRICTED = 1
        const val STATUS_CLOSED = 2
    }
}
