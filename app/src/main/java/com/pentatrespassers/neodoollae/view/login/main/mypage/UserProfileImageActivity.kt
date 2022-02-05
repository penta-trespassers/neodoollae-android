package com.pentatrespassers.neodoollae.view.login.main.mypage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.pentatrespassers.neodoollae.databinding.ActivityUserProfileImageBinding
import com.pentatrespassers.neodoollae.dto.User
import com.pentatrespassers.neodoollae.lib.Authentication
import splitties.bundle.BundleSpec
import splitties.bundle.bundle
import splitties.bundle.withExtras

class UserProfileImageActivity : AppCompatActivity() {

    object Extras : BundleSpec(){
        var profileImage: String by bundle()
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
        with(bind){
            backButtonRoomUserProfileImage.setOnClickListener{
                onBackPressed()
            }

            Glide.with(root)
                .load(profileImage)
                .into(userProfileImage)

            setContentView(root)
        }
    }

}