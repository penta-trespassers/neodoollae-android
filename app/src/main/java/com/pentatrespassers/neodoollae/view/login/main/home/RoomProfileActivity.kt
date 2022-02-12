package com.pentatrespassers.neodoollae.view.login.main.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.databinding.ActivityRoomProfileBinding
import com.pentatrespassers.neodoollae.dto.Reservation
import com.pentatrespassers.neodoollae.dto.Room
import com.pentatrespassers.neodoollae.dto.User
import com.pentatrespassers.neodoollae.lib.Authentication
import com.pentatrespassers.neodoollae.lib.Util.gone
import com.pentatrespassers.neodoollae.lib.Util.show
import com.pentatrespassers.neodoollae.view.login.main.home.roomprofile.RoomImageAdapter
import com.pentatrespassers.neodoollae.view.login.main.home.roomprofile.InvitationEditActivity
import com.pentatrespassers.neodoollae.view.login.main.home.roomprofile.ReservationEditActivity
import com.pentatrespassers.neodoollae.view.login.main.home.roomprofile.ReservationEditActivity.Extras.reservation
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

            titleTextRoomProfile.text =  getString(if (roomInfo.userId == user.id) R.string.room_of_mine else R.string.room_of_friend)

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

            profileCellRoomProfile.setProfileView(roomInfo)
//            with(profileCellRoomProfile) {
//                Glide.with(this@RoomProfileActivity)
//                    .load(getMainImage())
//                    .error(R.drawable.ic_common_bed)
//                    .into(profileImage)
//                profileImage.setOnClickListener {
//                    start<ShowImageActivity> {
//                        putExtras(ShowImageActivity.Extras) {
//                            this.profileImage = getMainImage()
//                        }
//                    }
//                }
//                nameText.text = roomInfo.roomName
//
//                RetrofitClient.getUserById(roomInfo.userId!!) { _, response ->
//                    host = response.body()!!
//                    Glide.with(this@RoomProfileActivity)
//                        .load(host.profileImage)
//                        .into(hostImageRoomProfileCell)
//                    hostNameTextRoomProfileCell.text = host.nickname
//                }
//
//                hostButtonRoomProfileCell.setOnClickListener {
//                    start<FriendProfileActivity> {
//                        putExtras(FriendProfileActivity.Extras) {
//                            this.user = host
//                        }
//                    }
//                }
//
//                // roomScoreImage.setImageDrawable()
//                roomScoreButton.setOnClickListener {
//                    start<ReviewActivity>()
//                }
//
//            }
            roomStateContent.text = when (roomInfo.status) {
                0 -> "개방"
                1 -> "제한"
                2 -> "폐쇄"
                else -> "NONE"
            }

            roomAddressContent.text =
                getString(R.string.full_Address, roomInfo.address, roomInfo.detailAddress)

            wordFromHostContent.text = roomInfo.description

            when (hasNoImage()) {
                true -> roomImageConstraint.gone()
                false -> {
                    roomImageConstraint.show()
                    roomImageRecycler.adapter =
                        RoomImageAdapter(this@RoomProfileActivity, roomInfo.roomImages!!)
                }
            }

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
                if(user.id == roomInfo.userId) {
                    start<InvitationEditActivity> {
                        putExtras(InvitationEditActivity.Extras) {
                            invitation = Reservation(
                                0,
                                user.id,
                                roomInfo.id!!,
                                user.nickname,
                                roomInfo.roomName
                            )
                        }
                    }
                }
                else{
                    start<ReservationEditActivity> {
                        putExtras(InvitationEditActivity.Extras) {
                            reservation = Reservation(
                                0,
                                user.id,
                                roomInfo.id!!,
                                user.nickname,
                                roomInfo.roomName
                            )
                        }
                    }
                }
            }


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