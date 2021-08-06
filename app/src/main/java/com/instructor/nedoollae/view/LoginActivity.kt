package com.instructor.nedoollae.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.instructor.nedoollae.databinding.ActivityMainBinding
import com.instructor.nedoollae.lib.Util
import com.instructor.nedoollae.view.login.HomeActivity
import splitties.activities.start
import splitties.toast.toast

class LoginActivity : AppCompatActivity() {

    private val bind by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Util.j("sdfdsf")

        with (bind) {
            setContentView(root)
            testAButton.setOnClickListener {
                toast("테스트")
            }
            testBButton.setOnClickListener {
                start<HomeActivity>()
            }
        }

    }
}