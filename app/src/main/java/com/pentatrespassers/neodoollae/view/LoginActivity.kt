package com.pentatrespassers.neodoollae.view

import android.Manifest
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.coroutineScope
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.auth.TokenManager
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.util.Utility
import com.kakao.sdk.user.UserApiClient
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.databinding.ActivityLoginBinding
import com.pentatrespassers.neodoollae.lib.Authentication
import com.pentatrespassers.neodoollae.lib.UserPreferences
import com.pentatrespassers.neodoollae.lib.Util
import com.pentatrespassers.neodoollae.lib.Util.hide
import com.pentatrespassers.neodoollae.lib.Util.show
import com.pentatrespassers.neodoollae.network.RetrofitClient
import com.pentatrespassers.neodoollae.view.login.MainActivity
import com.pentatrespassers.neodoollae.view.login.RegisterActivity
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import splitties.activities.start
import splitties.alertdialog.appcompat.alertDialog
import splitties.alertdialog.appcompat.coroutines.DialogButton
import splitties.alertdialog.appcompat.coroutines.showAndAwait
import splitties.bundle.putExtras
import splitties.permissions.ensureAllPermissions


class LoginActivity : AppCompatActivity() {

    private val permissions = listOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    private val bind by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.white)
        with(bind) {
            setContentView(root)
            if (Authentication.accessToken != null) {
                Authentication.ifLoggedIn({
                    progressBar.hide()
                    init()
                }) {
                    Util.j("????????? ?????? ${Authentication.accessToken}")
                    start<MainActivity>()
                    finish()
                }
                progressBar.show()
            } else {
                init()
            }

        }

    }

    private fun init(){

        Util.j("??? ??????: " + Utility.getKeyHash(this@LoginActivity))
        bind.kakaoLoginButton.setOnClickListener {
            UserApiClient.instance.run {
                if (isKakaoTalkLoginAvailable(this@LoginActivity)) {
                    loginWithKakaoTalk(this@LoginActivity, callback = kakaoLoginCallback)
                } else {
                    loginWithKakaoAccount(this@LoginActivity, callback = kakaoLoginCallback)
                }
            }
        }
        lifecycle.coroutineScope.launch {
            ensureAllPermissions(
                permissions,
                activity = this@LoginActivity,
                fragmentManager = supportFragmentManager,
                lifecycle = lifecycle,
                showRationaleBeforeFirstAsk = true,
                showRationaleAndContinueOrReturn = {
                    alertDialog(
                        title = "???????????? ??????",
                        message = "????????? ??????"
                    ).showAndAwait(
                        true,
                        negativeButton = DialogButton("??????", false),
                        dismissValue = false
                    )
                },
                askOpenSettingsOrReturn = {
                    alertDialog(
                        title = "?????? ??????",
                        message = "?????? ??????"
                    ).showAndAwait(
                        okValue = true,
                        negativeButton = DialogButton("?????? ??????", false),
                        dismissValue = true
                    )
                }
            ) {
                finish()
                suspendCancellableCoroutine<Nothing> { c -> c.cancel() }
            }




            if (AuthApiClient.instance.hasToken()) {
                UserApiClient.instance.accessTokenInfo { _, error ->
                    if (error != null) {
                        needLogin()
                    }
                    // ???????????? ???????????? ???????????? ??????
                    else {
                        loggedInKakao()
                    }
                }
            } else {
                needLogin()
            }

        }
    }

    private fun needLogin() {
        with(bind) {
            kakaoLoginButton.visibility = View.VISIBLE
        }
    }

    private fun loggedInKakao() {
        Authentication.withFcmToken { fcmToken ->
            Util.j("kakao ??????: ${TokenManager.instance.getToken()?.accessToken}")
            Util.j("fcm ??????: $fcmToken")
            RetrofitClient.kakaoLogin(TokenManager.instance.getToken()?.accessToken, fcmToken) { _, response ->
                val accessToken = response.body()?.access
                UserPreferences.accessToken = accessToken
                // ?????? ????????? ??????
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
                    loginSuccess(accessToken)
                }
            }
        }

    }

    private fun loginSuccess(accessToken: String) {
        bind.progressBar.visibility = View.VISIBLE
        Authentication.startMainActivity(this, accessToken) {
            finish()
        }
    }

    private val kakaoLoginCallback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            Util.j("????????? ??????: $error")
        } else if (token != null) {
            loggedInKakao()
        }
    }
}