package com.pentatrespassers.neodoollae.view.login.main.friend.friendlist.friendprofile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pentatrespassers.neodoollae.databinding.ActivityReviewBinding
import com.pentatrespassers.neodoollae.dto.Room
import com.pentatrespassers.neodoollae.dto.User
import splitties.bundle.BundleSpec
import splitties.bundle.bundle
import splitties.bundle.withExtras


class ReviewActivity : AppCompatActivity() {

    object Extras : BundleSpec() {
        var user: User by bundle()
        var room: Room by bundle()
    }

    private val user by lazy {
        withExtras(Extras) {
            user
        }
    }

    private val room by lazy {
        withExtras(Extras) {
            room
        }
    }

    private val bind by lazy {
        ActivityReviewBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(bind) {

            backButtonReview.setOnClickListener {
                onBackPressed()
            }


            setContentView(root)
        }
    }
}