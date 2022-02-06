package com.pentatrespassers.neodoollae.view.login.main.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pentatrespassers.neodoollae.databinding.ActivityEntireScheduleBinding

class EntireScheduleActivity : AppCompatActivity() {

    private val bind by lazy {
        ActivityEntireScheduleBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(bind) {
            setContentView(root)
        }
    }
}