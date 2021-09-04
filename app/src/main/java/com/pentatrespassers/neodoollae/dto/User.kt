package com.pentatrespassers.neodoollae.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    var id: Int? = null,
    var nickname: String = "", val socialId: Int = -1, val socialType: Int = -1,
    var profileImage: String = "", val createdAt: Long = 0, val fcmToken: String = "",
    @SerializedName("friend_code")
    val friendCode: String = ""
) : Parcelable {
    companion object {
        const val SOCIAL_TYPE_KAKAO = 0
    }
}
