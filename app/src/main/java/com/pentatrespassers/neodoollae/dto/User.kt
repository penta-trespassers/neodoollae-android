package com.pentatrespassers.neodoollae.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    var id: Int = ID_UNDEFINED,
    var nickname: String = "", val socialId: Int = -1, val socialType: Int = -1,
    var profileImage: String = "", val createdAt: Long = 0, val fcmToken: String = "",
    val friendCode: String = "", val hasRoom: Boolean = false
) : Parcelable {
    companion object {
        const val ID_UNDEFINED = -1
        const val SOCIAL_TYPE_KAKAO = 0
    }
}
