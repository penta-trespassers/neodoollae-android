package com.pentatrespassers.neodoollae.view.login.main.friend.friendlist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.common.adapter.RoomCardAdapter
import com.pentatrespassers.neodoollae.databinding.ActivityFriendProfileBinding
import com.pentatrespassers.neodoollae.dto.User
import com.pentatrespassers.neodoollae.lib.Authentication
import com.pentatrespassers.neodoollae.lib.Util.gone
import com.pentatrespassers.neodoollae.lib.Util.show
import com.pentatrespassers.neodoollae.network.RetrofitClient
import com.pentatrespassers.neodoollae.view.login.main.friend.friendlist.friendprofile.ReviewActivity
import com.pentatrespassers.neodoollae.view.login.main.mypage.ShowImageActivity
import splitties.activities.start
import splitties.bundle.BundleSpec
import splitties.bundle.bundle
import splitties.bundle.putExtras
import splitties.bundle.withExtras

class FriendProfileActivity : AppCompatActivity() {

    object Extras : BundleSpec() {
        var user: User by bundle()
    }

    private val user by lazy {
        withExtras(Extras) {
            user
        }
    }

    private val myId: Int = Authentication.user!!.id

    private val bind by lazy {
        ActivityFriendProfileBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(bind) {
            backButtonFriendProfile.setOnClickListener {
                finish()
            }

            when (user.id) {
                myId -> {
                    titleTextFriendProfile.text = getString(R.string.my_profile)
                    favoriteButtonFriendProfile.gone()
                }
                else -> {
                    titleTextFriendProfile.text = getString(R.string.friend_profile)
                    favoriteButtonFriendProfile.show()
                }
            }

            favoriteButtonFriendProfile.setOnClickListener {

            }

            profileCellFriendProfile.setProfileView(user)

            RetrofitClient.getRoom(user.id) { _, response ->
                when(response.body().isNullOrEmpty()){
                    true -> {
                        roomCardRecycler.gone()
                        hasNoRoomText.show()
                    }
                    false -> {
                        roomCardRecycler.adapter =
                            RoomCardAdapter(this@FriendProfileActivity, response.body()!!)
                        hasNoRoomText.gone()
                    }
                }

            }

            setContentView(root)
        }

    }
}