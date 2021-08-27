package com.pentatrespassers.neodoollae.view.login.main.friend

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pentatrespassers.neodoollae.databinding.ActivityFriendProfileBinding
import com.pentatrespassers.neodoollae.dto.RoomInfo
import com.pentatrespassers.neodoollae.view.login.main.friend.friendprofile.RoomCardAdapter

class FriendProfileActivity : AppCompatActivity() {

    private val bind by lazy {
        ActivityFriendProfileBinding.inflate(layoutInflater)
    }
    private val roomList = arrayListOf(
        RoomInfo(roomName = "우리 집", status = RoomInfo.STATUS_OPEN),
        RoomInfo(roomName = "우리 집", status = RoomInfo.STATUS_CLOSED),
        RoomInfo(roomName = "우리 집", status = RoomInfo.STATUS_RESTRICTED),
        RoomInfo()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(bind) {
            setContentView(root)
            roomCardRecycler.adapter = RoomCardAdapter(this@FriendProfileActivity, roomList)
        }

    }
}