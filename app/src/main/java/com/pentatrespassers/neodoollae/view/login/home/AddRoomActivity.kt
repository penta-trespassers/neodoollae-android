package com.pentatrespassers.neodoollae.view.login.home

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.databinding.ActivityAddRoomBinding
import com.pentatrespassers.neodoollae.lib.Util
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
    private val fragmentList = arrayListOf(addressFragment, roomInfoFragment, pictureFragment)
    private var currentFragmentIndex = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(bind) {
            setContentView(root)
            fragmentTransaction() {
                add(R.id.addRoomFrame, addressFragment)
                add(R.id.addRoomFrame, roomInfoFragment)
                add(R.id.addRoomFrame, pictureFragment)
                hide(roomInfoFragment)
                hide(pictureFragment)
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
                    Util.j(addressFragment.address)
                    // TODO 완료 시 할 작업
                } else {
                    fragmentTransaction {
                        hide(fragmentList[currentFragmentIndex])
                        show(fragmentList[++currentFragmentIndex])
                        if (currentFragmentIndex == 1) {
                            prevButton.visibility = View.VISIBLE
                        }else if (currentFragmentIndex == fragmentList.lastIndex) {
                            nextButton.setImageResource(R.drawable.ic_done_24dp)
                        }
                    }
                }
            }
        }
    }
}