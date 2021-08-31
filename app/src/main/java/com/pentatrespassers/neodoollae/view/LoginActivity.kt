package com.pentatrespassers.neodoollae.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.auth.TokenManager
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.util.Utility
import com.kakao.sdk.user.UserApiClient
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.databinding.ActivityLoginBinding
import com.pentatrespassers.neodoollae.dto.Token
import com.pentatrespassers.neodoollae.lib.Authentication
import com.pentatrespassers.neodoollae.lib.Util
import com.pentatrespassers.neodoollae.network.RetrofitClient
import com.pentatrespassers.neodoollae.view.login.MainActivity
import com.pentatrespassers.neodoollae.view.login.RegisterActivity
import splitties.activities.start
import splitties.bundle.putExtras

class LoginActivity : AppCompatActivity() {

    private val bind by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Neodoollae)
        super.onCreate(savedInstanceState)

        with(bind) {
            setContentView(root)

            Util.j("키 해시: " + Utility.getKeyHash(this@LoginActivity))

            if (AuthApiClient.instance.hasToken()) {
                UserApiClient.instance.accessTokenInfo { _, error ->
                    if (error != null) {
                        needLogin()
                    }
                    // 카카오로 로그인이 되어있는 경우
                    else {
                        loggedInKakao()
                    }
                }
            } else {
                needLogin()
            }

            kakaoLoginButton.setOnClickListener {
                UserApiClient.instance.run {
                    if (isKakaoTalkLoginAvailable(this@LoginActivity)) {
                        loginWithKakaoTalk(this@LoginActivity, callback = kakaoLoginCallback)
                    } else {
                        loginWithKakaoAccount(this@LoginActivity, callback = kakaoLoginCallback)
                    }
                }
            }

        }
    }

    private fun needLogin() {
        with(bind) {
            kakaoLoginButton.visibility = View.VISIBLE
        }
    }

    private fun loggedInKakao() {
        RetrofitClient.kakaoLogin(Token(access = TokenManager.instance.getToken()?.accessToken))
            .enqueue(RetrofitClient.defaultCallback { _, response ->
                val accessToken = response.body()?.access
                // 회원 정보가 없음
                if (accessToken == null) {
                    UserApiClient.instance.me { user, error ->
                        start<RegisterActivity> {
                            putExtras(RegisterActivity.Extras) {
                                nickname = user?.kakaoAccount?.profile?.nickname ?: ""
                            }
                        }
                        finish()
                    }
                } else {
                    Authentication.accessToken = accessToken
                    loginSuccess()
                }
            })
    }

    private fun loginSuccess() {
        bind.progressBar.visibility = View.VISIBLE
        start<MainActivity>()
        finish()
    }

    private val kakaoLoginCallback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            Util.j("로그인 실패: $error")
        } else if (token != null) {
            loggedInKakao()
        }
    }
}