package com.pentatrespassers.neodoollae.view.login.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pentatrespassers.neodoollae.databinding.ActivityRoomProfileBinding

class RoomProfileActivity : AppCompatActivity() {

    private val bind by lazy {
        ActivityRoomProfileBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with (bind) {
            setContentView(root)
        }
    }
}