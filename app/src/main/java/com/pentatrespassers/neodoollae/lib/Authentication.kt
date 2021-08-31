package com.pentatrespassers.neodoollae.lib

object Authentication {
    var accessToken: String? = null
        get() = "Bearer $field"
    var nickname: String? = null
}