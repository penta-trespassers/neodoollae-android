package com.pentatrespassers.neodoollae.view.login.home

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.databinding.ActivityAddRoomBinding
import com.pentatrespassers.neodoollae.dto.RoomInfo
import com.pentatrespassers.neodoollae.view.login.home.addroom.AddressFragment
import com.pentatrespassers.neodoollae.view.login.home.addroom.PictureFragment
import com.pentatrespassers.neodoollae.view.login.home.addroom.RoomCompleteFragment
import com.pentatrespassers.neodoollae.view.login.home.addroom.RoomInfoFragment
import splitties.activities.start
import splitties.bundle.putExtras
import splitties.fragments.fragmentTransaction

class AddRoomActivity : AppCompatActivity() {

    private val bind by lazy {
        ActivityAddRoomBinding.inflate(layoutInflater)
    }

    private val addressFragment by lazy {
        AddressFragment.newInstance()
    }
    private val roomInfoFragment by lazy {
        RoomInfoFragment.newInstance()
    }
    private val pictureFragment by lazy {
        PictureFragment.newInstance()
    }
    private val roomCompleteFragment by lazy {
        RoomCompleteFragment.newInstance()
    }
    private val fragmentList =
        arrayListOf(addressFragment, roomInfoFragment, pictureFragment, roomCompleteFragment)
    private var currentFragmentIndex = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(bind) {
            setContentView(root)
            fragmentTransaction() {
                for (i in fragmentList.indices) {
                    add(R.id.addRoomFrame, fragmentList[i])
                    if (i != 0) {
                        hide(fragmentList[i])
                    }
                }
            }

            homeButton.setOnClickListener {
                finish()
            }
            prevButton.setOnClickListener {
                fragmentTransaction {
                    if (currentFragmentIndex == fragmentList.lastIndex) {
                        nextButton.setImageResource(R.drawable.ic_arrow_right)
                    }
                    hide(fragmentList[currentFragmentIndex])
                    show(fragmentList[--currentFragmentIndex])
                    if (currentFragmentIndex == 0) {
                        prevButton.visibility = View.GONE
                    }
                }
            }
            nextButton.setOnClickListener {
                if (currentFragmentIndex == fragmentList.lastIndex) {
                    start<RoomProfileActivity> {
                        putExtras(RoomProfileActivity.Extras) {
                            roomInfo = RoomInfo(
                                roomName = roomInfoFragment.roomName,
                                address = addressFragment.address,
                                detailAddress = addressFragment.detailAddress,
                                description = roomInfoFragment.description
                            )
                        }
                    }
                } else {
                    fragmentTransaction {
                        hide(fragmentList[currentFragmentIndex])
                        show(fragmentList[++currentFragmentIndex])
                        if (currentFragmentIndex == 1) {
                            prevButton.visibility = View.VISIBLE
                        } else if (currentFragmentIndex == fragmentList.lastIndex) {
                            nextButton.setImageResource(R.drawable.ic_done_24dp)
                        }
                    }
                }
            }
        }
    }
}