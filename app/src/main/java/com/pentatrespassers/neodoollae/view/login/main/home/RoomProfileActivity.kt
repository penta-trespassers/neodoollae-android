package com.pentatrespassers.neodoollae.view.login.main.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.naver.maps.geometry.LatLng
import com.naver.maps.geometry.Utmk
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.databinding.ActivityRoomProfileBinding
import com.pentatrespassers.neodoollae.dto.Room
import com.pentatrespassers.neodoollae.dto.User
import com.pentatrespassers.neodoollae.lib.Authentication
import com.pentatrespassers.neodoollae.lib.Util
import com.pentatrespassers.neodoollae.lib.Util.gone
import com.pentatrespassers.neodoollae.lib.Util.show
import com.pentatrespassers.neodoollae.network.RetrofitClient
import com.pentatrespassers.neodoollae.view.login.main.friend.friendlist.FriendProfileActivity
import com.pentatrespassers.neodoollae.view.login.main.friend.friendlist.friendprofile.ReviewActivity
import com.pentatrespassers.neodoollae.view.login.main.home.roomactivity.RoomImageAdapter
import com.pentatrespassers.neodoollae.view.login.main.mypage.ShowImageActivity
import com.pentatrespassers.neodoollae.view.login.main.reservation.ReservationEditActivity
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

            when (roomInfo.userId) {
                user.id -> {
                    editButtonRoomProfile.show()
                    deleteButtonRoomProfile.show()
                    favoriteButtonRoomProfile.gone()
                }
                else -> {
                    editButtonRoomProfile.gone()
                    deleteButtonRoomProfile.gone()
                    favoriteButtonRoomProfile.show()
                }
            }

            editButtonRoomProfile.setOnClickListener {
                // TODO : edit my room
            }

            deleteButtonRoomProfile.setOnClickListener {
                // TODO : delete my room
            }

            favoriteButtonRoomProfile.setOnClickListener {
                // TODO : favorite room button
            }

            with(profileCellRoomProfile) {
                Glide.with(this@RoomProfileActivity)
                    .load(getMainImage())
                    .error(R.drawable.ic_common_bed)
                    .into(profileImage)
                profileImage.setOnClickListener {
                    start<ShowImageActivity> {
                        putExtras(ShowImageActivity.Extras) {
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

            roomImageConstraint.visibility = when (hasNoImage()) {
                true -> View.GONE
                false -> View.VISIBLE
            }

            roomImageRecycler.adapter =
                RoomImageAdapter(this@RoomProfileActivity, roomInfo.roomImages!!)

            showMapButtonRoomProfile.text = getString(R.string.view_on_map)

            showMapButtonRoomProfile.setOnClickListener {
                hyperlink(
                    "http://www.google.co.kr/maps/@" +
                            roomInfo.latitude.toString() + "," +
                            roomInfo.longitude.toString() + ",14z"
                )
            }

            reserveButtonRoomProfile.text = when (roomInfo.userId) {
                user.id -> getString(R.string.make_invitation)
                else -> getString(R.string.make_reservation)
            }

            reserveButtonRoomProfile.setOnClickListener {
                var intent = Intent(this@RoomProfileActivity, ReservationEditActivity::class.java)
             //   intent.putExtra("Reservation",nil)
                startActivity(intent)
            }


        }
    }

    private fun setTitle(): String {
        return when (roomInfo.userId) {
            user.id -> getString(R.string.room_of_mine)
            else -> getString(R.string.room_of_friend)
        }
    }

    private fun getMainImage(): String {
        return when (hasNoImage()) {
            true -> R.drawable.ic_common_bed.toString()
            false -> roomInfo.roomImages!![0]
        }
    }

    private fun hasNoImage(): Boolean {
        return roomInfo.roomImages.isNullOrEmpty()
    }

    private fun hyperlink(url: String) {
        intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}