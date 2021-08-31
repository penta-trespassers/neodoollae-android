package com.pentatrespassers.neodoollae.lib

object Authentication {
    var accessToken: String? = null
        get() = "Bearer $field"
        set(value) {
            Util.j("액세스 토큰: $value")
            field = value
        }
    var nickname: String? = null
}