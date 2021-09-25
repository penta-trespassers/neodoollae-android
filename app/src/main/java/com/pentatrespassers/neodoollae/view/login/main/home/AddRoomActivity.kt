package com.pentatrespassers.neodoollae.view.login.main.home

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.databinding.ActivityAddRoomBinding
import com.pentatrespassers.neodoollae.dto.Room
import com.pentatrespassers.neodoollae.view.login.main.home.addroom.*
import splitties.activities.start
import splitties.bundle.putExtras
import splitties.fragments.fragmentTransaction

class AddRoomActivity : AppCompatActivity() {

    private val bind by lazy {
        ActivityAddRoomBinding.inflate(layoutInflater)
    }

    private val addressFragment = AddressFragment.newInstance()
    private val roomInfoFragment = RoomInfoFragment.newInstance()
    private val roomOperationFragment = RoomOperationFragment.newInstance()
    private val pictureFragment = PictureFragment.newInstance()
    private val roomCompleteFragment = RoomCompleteFragment.newInstance()

    private val fragmentList =
        arrayListOf(
            addressFragment,
            roomInfoFragment,
            roomOperationFragment,
            pictureFragment,
            roomCompleteFragment
        )
    private var currentFragmentIndex = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(bind) {
            setContentView(root)

            fragmentTransaction {
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
                            room = Room(
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