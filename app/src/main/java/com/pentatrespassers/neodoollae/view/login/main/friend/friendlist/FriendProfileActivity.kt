package com.pentatrespassers.neodoollae.view.login.main.friend.friendlist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pentatrespassers.neodoollae.common.adapter.RoomCardAdapter
import com.pentatrespassers.neodoollae.databinding.ActivityFriendProfileBinding
import com.pentatrespassers.neodoollae.dto.RoomInfo
import com.pentatrespassers.neodoollae.dto.User
import splitties.bundle.BundleSpec
import splitties.bundle.bundle
import splitties.bundle.withExtras

class FriendProfileActivity : AppCompatActivity() {

    object Extras: BundleSpec() {
        var user: User by bundle()
    }

    private val user by lazy {
        withExtras(Extras) {
            user
        }
    }

    private val bind by lazy {
        ActivityFriendProfileBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(bind) {
            setContentView(root)
            RetrofitClient.getRoom(user.id) { _, response ->
                roomCardRecycler.adapter = RoomCardAdapter(this@FriendProfileActivity, response.body()!!)
            }

            nicknameTextFriendProfile.text = user.nickname
            backButton.setOnClickListener {
                finish()
            }
        }

    }
}