package com.pentatrespassers.neodoollae.common.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.common.view.ProfileCell.Type.values
import com.pentatrespassers.neodoollae.databinding.CellProfileBinding
import com.pentatrespassers.neodoollae.dto.Room
import com.pentatrespassers.neodoollae.dto.User
import com.pentatrespassers.neodoollae.lib.Util.gone
import com.pentatrespassers.neodoollae.lib.Util.show
import com.pentatrespassers.neodoollae.network.RetrofitClient
import com.pentatrespassers.neodoollae.view.login.main.friend.friendlist.FriendProfileActivity
import com.pentatrespassers.neodoollae.view.login.main.mypage.ShowImageActivity
import splitties.activities.start
import splitties.bundle.putExtras
import splitties.resources.str


class ProfileCell @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    enum class Type {
        USER,
        ROOM
    }

    var profileViewType: Enum<Type> = Type.USER

    init {
        initView()

        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.ProfileCell,
            0, 0
        ).apply {
            try {
                profileViewType = values()[getInt(R.styleable.ProfileCell_profileType, 0)]
            } finally {
                recycle()
            }
        }
    }

    private fun initView() {
        View.inflate(context, R.layout.cell_profile, this)

    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        with(CellProfileBinding.bind(this)) {
            when (profileViewType) {
                Type.USER -> {
                    roomHostImage.gone()
                    roomHostNameText.gone()
                    guestScoreImage.show()
                    guestScoreText.show()
                    guestScoreText.text = str(R.string.guest_score)
                    hostScoreText.text = str(R.string.host_score)
                }
                Type.ROOM -> {
                    roomHostImage.show()
                    roomHostNameText.show()
                    guestScoreImage.gone()
                    guestScoreText.gone()
                    hostScoreText.text = str(R.string.room_score)
                }
            }

        }
    }

    fun setProfileView(user: User?) {
        user?.let {
            setProfileImage(it.profileImage)
            setProfileName(it.nickname)
            setUserScore(it)
        }
    }

    fun setProfileView(room: Room) {
        when (room.roomImages.isNullOrEmpty()) {
            true -> setProfileImage("")
            false -> setProfileImage(room.roomImages!![0])
        }
        setProfileName(room.roomName)
        setRoomHost(room)
        setRoomScore(room)
    }

    private fun setProfileImage(image: String) {
        with(CellProfileBinding.bind(this)) {
            when (profileViewType) {
                Type.USER -> {
                    Glide.with(context)
                        .load(image)
                        .error(R.drawable.ic_common_account_no_padding)
                        .into(profileImage)
                }
                Type.ROOM -> {
                    Glide.with(context)
                        .load(image)
                        .error(R.drawable.ic_common_bed_circle)
                        .into(profileImage)
                }
            }

            profileImage.setOnClickListener {
                context.start<ShowImageActivity> {
                    putExtras(ShowImageActivity.Extras) {
                        this.profileImage = image
                    }
                }
            }
        }
    }

    private fun setProfileName(name: String) {
        with(CellProfileBinding.bind(this)) {
            profileNameText.text = name
        }
    }

    private fun setRoomHost(room: Room) {
        with(CellProfileBinding.bind(this)) {
            RetrofitClient.getUserById(room.userId!!) { _, response ->
                val host = response.body()!!
                Glide.with(context)
                    .load(host.profileImage)
                    .into(roomHostImage)
                roomHostNameText.text = host.nickname
                guestScoreButton.setOnClickListener {
                    context.start<FriendProfileActivity> {
                        putExtras(FriendProfileActivity.Extras) {
                            this.user = host
                        }
                    }
                }
            }
        }
    }

    private fun setUserScore(user: User) {
        with(CellProfileBinding.bind(this)) {
            guestScoreButton.setOnClickListener {  }
            hostScoreButton.setOnClickListener {  }
        }
    }

    private fun setRoomScore(room: Room) {
        with(CellProfileBinding.bind(this)) {
            hostScoreButton.setOnClickListener {

            }
        }
    }
}
