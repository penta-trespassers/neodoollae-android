package com.pentatrespassers.neodoollae

import android.app.Application
import com.google.firebase.messaging.FirebaseMessaging
import com.kakao.sdk.common.KakaoSdk
import com.pentatrespassers.neodoollae.lib.Param
import com.pentatrespassers.neodoollae.lib.Util

class GlobalApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, Param.KAKAO_SDK_APP_KEY)
        FirebaseMessaging.getInstance().token.addOnCompleteListener {
            if (!it.isSuccessful) {
                Util.j("Failure: FCM Token")
                return@addOnCompleteListener
            }

            // Get new FCM registration token
            val token = it.result

            // Log and toast
            Util.j("FCM Token: $token")
        }
    }
}