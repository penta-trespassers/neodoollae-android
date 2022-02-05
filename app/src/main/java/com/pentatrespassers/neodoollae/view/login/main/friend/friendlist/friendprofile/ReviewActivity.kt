package com.pentatrespassers.neodoollae.view.login.main.friend.friendlist.friendprofile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pentatrespassers.neodoollae.databinding.ActivityReviewBinding


class ReviewActivity : AppCompatActivity() {

    private val bind by lazy {
        ActivityReviewBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(bind){

            backButtonReview.setOnClickListener{
                onBackPressed()
            }


            setContentView(root)
        }
    }
}