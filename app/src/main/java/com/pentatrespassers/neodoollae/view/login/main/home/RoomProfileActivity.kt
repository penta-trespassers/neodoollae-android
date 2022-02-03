package com.pentatrespassers.neodoollae.view.login.main.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.databinding.ActivityRoomProfileBinding
import com.pentatrespassers.neodoollae.dto.Room
import splitties.bundle.BundleSpec
import splitties.bundle.bundle
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

    private val bind by lazy {
        ActivityRoomProfileBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(bind) {
            setContentView(root)

//            profileCellRoomProfile.nameText.text = roomInfo.roomName
//
//            roomAddressContent.text =
//                getString(R.string.full_Address, roomInfo.address, roomInfo.detailAddress)
//
//            wordFromHostContent.text = roomInfo.description
        }
    }
}