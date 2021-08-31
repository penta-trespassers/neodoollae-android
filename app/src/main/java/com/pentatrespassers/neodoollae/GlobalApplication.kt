package com.pentatrespassers.neodoollae

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, "adb53e0f4981741cc20cfd3c0d6d3dfa")
    }
}