package com.pentatrespassers.neodoollae.view.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kakao.sdk.auth.TokenManager
import com.pentatrespassers.neodoollae.databinding.ActivityRegisterBinding
import com.pentatrespassers.neodoollae.lib.Authentication
import com.pentatrespassers.neodoollae.network.RetrofitClient
import splitties.activities.start
import splitties.bundle.BundleSpec
import splitties.bundle.bundleOrDefault
import splitties.bundle.withExtras

class RegisterActivity : AppCompatActivity() {

    object Extras : BundleSpec() {
        var nickname: String by bundleOrDefault("")
    }

    val nickname by lazy {
        withExtras(Extras) {
            nickname
        }
    }

    private val bind by lazy {
        ActivityRegisterBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(bind) {
            setContentView(root)
            nicknameEditText.setText(nickname)
            registerButton.setOnClickListener {
                RetrofitClient.kakaoRegister(
                    TokenManager.instance.getToken()?.accessToken, nicknameEditText.text.toString()
                ).enqueue(RetrofitClient.defaultCallback { _, response ->
                    val access = response.body()?.access
                    if (access != null) {
                        Authentication.accessToken = access
                        start<MainActivity>()
                    }
                })
            }

        }

    }
}