package com.instructor.nedoollae.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.instructor.nedoollae.databinding.ActivityLoginBinding
import com.instructor.nedoollae.view.login.MainActivity
import com.instructor.nedoollae.view.login.home.AddRoomActivity
import splitties.activities.start

class LoginActivity : AppCompatActivity() {

    private val bind by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with (bind) {
            setContentView(root)
            dummyLoginButton.setOnClickListener {
                start<MainActivity>()
            }
            dummyAddRoomButton.setOnClickListener {
                start<AddRoomActivity>()
            }
        }

    }
}