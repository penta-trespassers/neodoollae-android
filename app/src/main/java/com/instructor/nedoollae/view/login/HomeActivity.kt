package com.instructor.nedoollae.view.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.instructor.nedoollae.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private val bind by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(bind) {
            setContentView(root)
        }

    }
}