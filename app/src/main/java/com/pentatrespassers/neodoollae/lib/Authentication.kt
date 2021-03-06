package com.pentatrespassers.neodoollae.lib

import android.content.Context
import com.google.firebase.messaging.FirebaseMessaging
import com.pentatrespassers.neodoollae.dto.User
import com.pentatrespassers.neodoollae.network.RetrofitClient
import com.pentatrespassers.neodoollae.view.login.MainActivity
import splitties.activities.start

object Authentication {
    var accessToken: String? = UserPreferences.accessToken
        set(value) {
            Util.j("액세스 토큰: $value")
            field = value
        }
    val bearerAccessToken: String
        get() = "Bearer $accessToken"

    var user: User? = null

    val uid: Int?
        get() = user?.id

    fun startMainActivity(context: Context, accessToken: String, extraCallback: () -> Unit = {}) {
        this.accessToken = accessToken
        RetrofitClient.getMyInfo{ _, response ->
            user = response.body()
            context.start<MainActivity>()
            extraCallback()
        }
    }
    fun refreshUser(callback: (User) -> Unit = {}) {
        RetrofitClient.getMyInfo { _, response ->
            user = response.body()
            callback(user!!)
        }
    }

    fun withFcmToken(callback: (fcmToken: String) -> Unit) {
        FirebaseMessaging.getInstance().token.addOnCompleteListener {
            if (!it.isSuccessful) {
                Util.j("Failure: FCM Token")
                return@addOnCompleteListener
            }
            // Get new FCM registration token
            callback(it.result)
        }
    }

    fun ifLoggedIn(loggedOutCallback: () -> Unit = {}, loggedInCallback: () -> Unit = {}) {
        if (accessToken == null) {
            loggedOutCallback()
            return
        } else {
            RetrofitClient.getMyInfo({ _, _ ->
                loggedOutCallback()
            }) { _, response ->
                user = response.body()
                loggedInCallback()
            }
        }
    }
}