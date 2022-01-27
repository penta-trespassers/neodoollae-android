package com.pentatrespassers.neodoollae.view.login.main.mypage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.databinding.ActivityEditMyInfoBinding

class EditMyInfoActivity : AppCompatActivity() {

    private val bind by lazy {
        ActivityEditMyInfoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(bind){
            backButtonEditMyInfo.setOnClickListener{
                finish()
            }
            setContentView(root)
        }
    }
}