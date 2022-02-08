package com.pentatrespassers.neodoollae.view.login.main.mypage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pentatrespassers.neodoollae.databinding.ActivityVisitHistoryBinding

class VisitHistoryActivity : AppCompatActivity() {

    private val bind by lazy {
        ActivityVisitHistoryBinding.inflate(layoutInflater)
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(bind) {
            setContentView(root)

            backButtonVisit.setOnClickListener {
                finish()
            }
        }
    }
}