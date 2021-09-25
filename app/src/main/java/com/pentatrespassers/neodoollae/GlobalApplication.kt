package com.pentatrespassers.neodoollae

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import com.pentatrespassers.neodoollae.lib.Param

class GlobalApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, Param.KAKAO_SDK_APP_KEY)
    }
}