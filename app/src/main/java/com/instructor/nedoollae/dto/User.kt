package com.instructor.nedoollae.dto

data class User(
    var nickname: String = "", val socialId: Int = -1, val socialType: Int = -1,
    var profileImage: String = "", val createdAt: Long = 0, val fcmToken: String = "",
    val friendCode: String = ""
) {
    companion object {
        const val SOCIAL_TYPE_KAKAO = 0
    }
}
