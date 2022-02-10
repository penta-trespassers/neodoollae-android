package com.pentatrespassers.neodoollae.view.login.main.mypage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.pentatrespassers.neodoollae.databinding.ActivityUserProfileImageBinding
import splitties.bundle.BundleSpec
import splitties.bundle.bundleOrNull
import splitties.bundle.withExtras

class ShowImageActivity : AppCompatActivity() {

    object Extras : BundleSpec() {
        var profileImage: String? by bundleOrNull()
    }

    private val profileImage by lazy {
        withExtras(Extras) {
            profileImage
        }
    }

    private val bind by lazy {
        ActivityUserProfileImageBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(bind) {
            backButtonRoomUserProfileImage.setOnClickListener {
                onBackPressed()
            }

            Glide.with(root)
                .load(profileImage)
                .into(userProfileImage)

            setContentView(root)
        }
    }

}