package com.pentatrespassers.neodoollae.firebase

import com.google.firebase.messaging.FirebaseMessagingService
import com.pentatrespassers.neodoollae.lib.Util

class MessagingService: FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Util.j("onNewToken: $token")
    }
}