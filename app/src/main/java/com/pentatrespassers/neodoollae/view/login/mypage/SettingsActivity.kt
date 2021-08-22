package com.pentatrespassers.neodoollae.view.login.mypage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pentatrespassers.neodoollae.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {

    private val bind by lazy {
        ActivitySettingsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(bind) {

            setContentView(root)
        }
    }
}