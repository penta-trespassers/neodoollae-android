package com.instructor.nedoollae.view.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.instructor.nedoollae.databinding.ActivityMapBinding

class MapActivity : AppCompatActivity() {

    private val bind by lazy {
        ActivityMapBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(bind) {
            setContentView(root)

        }

    }
}