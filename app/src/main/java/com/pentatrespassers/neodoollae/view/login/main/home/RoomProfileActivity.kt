package com.pentatrespassers.neodoollae.view.login.main.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.databinding.ActivityRoomProfileBinding
import com.pentatrespassers.neodoollae.dto.RoomInfo
import splitties.bundle.BundleSpec
import splitties.bundle.bundle
import splitties.bundle.withExtras

class RoomProfileActivity : AppCompatActivity() {

    object Extras : BundleSpec() {
        var roomInfo: RoomInfo by bundle()
    }
    private val roomInfo by lazy {
        withExtras(Extras) {
            roomInfo
        }
    }

    private val bind by lazy {
        ActivityRoomProfileBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with (bind) {
            setContentView(root)
            roomNameText.text = roomInfo.roomName
            addressText.text = getString(R.string.full_Address, roomInfo.address, roomInfo.detailAddress)
            descriptionText.text = roomInfo.description
        }
    }
}