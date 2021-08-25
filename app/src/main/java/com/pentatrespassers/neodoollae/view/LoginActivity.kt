package com.pentatrespassers.neodoollae.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pentatrespassers.neodoollae.databinding.ActivityLoginBinding
import com.pentatrespassers.neodoollae.view.login.MainActivity
import com.pentatrespassers.neodoollae.view.login.main.friend.FriendProfileActivity
import com.pentatrespassers.neodoollae.view.login.main.home.AddRoomActivity
import splitties.activities.start

class LoginActivity : AppCompatActivity() {

    private val bind by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(bind) {
            setContentView(root)
            dummyLoginButton.setOnClickListener {
                start<MainActivity>()
            }
            dummyAddRoomButton.setOnClickListener {
                start<AddRoomActivity>()
            }
            dummyFriendProfileButton.setOnClickListener {
                start<FriendProfileActivity>()
            }
        }

    }
}