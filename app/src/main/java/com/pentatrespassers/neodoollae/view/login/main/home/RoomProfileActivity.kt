package com.pentatrespassers.neodoollae.view.login.main.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.databinding.ActivityRoomProfileBinding
import com.pentatrespassers.neodoollae.dto.Room
import com.pentatrespassers.neodoollae.dto.User
import com.pentatrespassers.neodoollae.lib.Authentication
import com.pentatrespassers.neodoollae.lib.Util
import com.pentatrespassers.neodoollae.network.RetrofitClient
import com.pentatrespassers.neodoollae.view.login.main.friend.friendlist.FriendProfileActivity
import com.pentatrespassers.neodoollae.view.login.main.friend.friendlist.friendprofile.ReviewActivity
import com.pentatrespassers.neodoollae.view.login.main.mypage.UserProfileImageActivity
import splitties.activities.start
import splitties.bundle.BundleSpec
import splitties.bundle.bundle
import splitties.bundle.putExtras
import splitties.bundle.withExtras

class RoomProfileActivity : AppCompatActivity() {

    object Extras : BundleSpec() {
        var room: Room by bundle()
    }

    private val roomInfo by lazy {
        withExtras(Extras) {
            room
        }
    }

    private var user = Authentication.user!!

    private lateinit var host: User


    private val bind by lazy {
        ActivityRoomProfileBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(bind) {
            setContentView(root)

            backButtonRoomProfile.setOnClickListener {
                onBackPressed()
            }

            titleTextRoomProfile.text = setTitle()
            with(profileCellRoomProfile) {
                Glide.with(this@RoomProfileActivity)
                    .load(getMainImage())
                    .error(R.drawable.ic_common_bed)
                    .into(profileImage)
                profileImage.setOnClickListener {
                    start<UserProfileImageActivity> {
                        putExtras(UserProfileImageActivity.Extras) {
                            this.profileImage = getMainImage()
                        }
                    }
                }
                nameText.text = roomInfo.roomName

                RetrofitClient.getUserById(roomInfo.userId!!) { _, response ->
                    host = response.body()!!
                    Glide.with(this@RoomProfileActivity)
                        .load(host.profileImage)
                        .into(hostImageRoomProfileCell)
                    hostNameTextRoomProfileCell.text = host.nickname
                }

                hostButtonRoomProfileCell.setOnClickListener {
                    start<FriendProfileActivity> {
                        putExtras(FriendProfileActivity.Extras) {
                            this.user = host
                        }
                    }
                }

                // roomScoreImage.setImageDrawable()
                roomScoreButton.setOnClickListener {
                    start<ReviewActivity>()
                }

            }
            roomStateContent.text = when (roomInfo.status) {
                0 -> "개방"
                1 -> "제한"
                2 -> "폐쇄"
                else -> "NONE"
            }

            roomAddressContent.text =
                getString(R.string.full_Address, roomInfo.address, roomInfo.detailAddress)

            wordFromHostContent.text = roomInfo.description
        }
    }
    private fun setTitle() : String {
        return when (roomInfo.userId) {
            user.id -> getString(R.string.room_of_mine)
            else -> getString(R.string.room_of_friend)
        }
    }

    private fun getMainImage(): String {
        return when(roomInfo.roomImages.isNullOrEmpty()){
            true -> R.drawable.ic_common_bed.toString()
            false -> roomInfo.roomImages!![0]
        }
    }
}