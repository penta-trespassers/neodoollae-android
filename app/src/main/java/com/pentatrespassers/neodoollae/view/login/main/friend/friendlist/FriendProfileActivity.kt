package com.pentatrespassers.neodoollae.view.login.main.friend.friendlist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.common.adapter.RoomCardAdapter
import com.pentatrespassers.neodoollae.databinding.ActivityFriendProfileBinding
import com.pentatrespassers.neodoollae.dto.User
import com.pentatrespassers.neodoollae.lib.Authentication
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

            titleTextFriendProfile.text = when (user.id) {
                myId -> getString(R.string.my_profile)
                else -> getString(R.string.friend_profile)
            }

            with(profileCellFriendProfile) {
                nameText.text = user.nickname

                Glide.with(this@FriendProfileActivity)
                    .load(user.profileImage)
                    .error(R.drawable.ic_common_account_no_padding)
                    .into(profileImage)

                profileImage.setOnClickListener {
                    start<ShowImageActivity>{
                        putExtras(ShowImageActivity.Extras){
                            this.profileImage = user.profileImage
                        }
                    }
                }

                guestScoreButton.setOnClickListener {
                    start<ReviewActivity>()
                }

                hostScoreButton.setOnClickListener {
                    start<ReviewActivity>()
                }
            }

            RetrofitClient.getRooms(user.id) { _, response ->
                roomCardRecycler.adapter =
                    RoomCardAdapter(this@FriendProfileActivity, response.body()!!)
            }

            setContentView(root)
        }

    }
}