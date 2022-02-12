package com.pentatrespassers.neodoollae.view.login.main.mypage.managereview.writablereview

import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.databinding.ActivityWriteVisitReviewBinding
import splitties.resources.color

class WriteVisitReviewActivity : AppCompatActivity() {

    private val bind by lazy {
        ActivityWriteVisitReviewBinding.inflate(layoutInflater)
    }

    var roomScore = 5
    var hostScore = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(bind) {
            setContentView(root)

            sentiment5ConstraintRoom.setOnClickListener {
                roomScore = 5
                makeRoomUnselect()
                it.backgroundTintList = ColorStateList.valueOf(color(R.color.color_card_pressed))
            }

            sentiment4ConstraintRoom.setOnClickListener {
                roomScore = 4
                makeRoomUnselect()
                it.backgroundTintList = ColorStateList.valueOf(color(R.color.color_card_pressed))
            }

            sentiment3ConstraintRoom.setOnClickListener {
                roomScore = 3
                makeRoomUnselect()
                it.backgroundTintList = ColorStateList.valueOf(color(R.color.color_card_pressed))
            }

            sentiment2ConstraintRoom.setOnClickListener {
                roomScore = 2
                makeRoomUnselect()
                it.backgroundTintList = ColorStateList.valueOf(color(R.color.color_card_pressed))
            }

            sentiment1ConstraintRoom.setOnClickListener {
                roomScore = 1
                makeRoomUnselect()
                it.backgroundTintList = ColorStateList.valueOf(color(R.color.color_card_pressed))
            }


            sentiment5ConstraintHost.setOnClickListener {
                hostScore = 5
                makeHostUnselect()
                it.backgroundTintList = ColorStateList.valueOf(color(R.color.color_card_pressed))
            }

            sentiment4ConstraintHost.setOnClickListener {
                hostScore = 4
                makeHostUnselect()
                it.backgroundTintList = ColorStateList.valueOf(color(R.color.color_card_pressed))
            }

            sentiment3ConstraintHost.setOnClickListener {
                hostScore = 3
                makeHostUnselect()
                it.backgroundTintList = ColorStateList.valueOf(color(R.color.color_card_pressed))
            }

            sentiment2ConstraintHost.setOnClickListener {
                hostScore = 2
                makeHostUnselect()
                it.backgroundTintList = ColorStateList.valueOf(color(R.color.color_card_pressed))
            }

            sentiment1ConstraintHost.setOnClickListener {
                hostScore = 1
                makeHostUnselect()
                it.backgroundTintList = ColorStateList.valueOf(color(R.color.color_card_pressed))
            }
        }
    }

    private fun makeRoomUnselect(){
        with(bind){
            sentiment5ConstraintRoom.backgroundTintList = ColorStateList.valueOf(color(R.color.white))
            sentiment4ConstraintRoom.backgroundTintList = ColorStateList.valueOf(color(R.color.white))
            sentiment3ConstraintRoom.backgroundTintList = ColorStateList.valueOf(color(R.color.white))
            sentiment2ConstraintRoom.backgroundTintList = ColorStateList.valueOf(color(R.color.white))
            sentiment1ConstraintRoom.backgroundTintList = ColorStateList.valueOf(color(R.color.white))
        }
    }

    private fun makeHostUnselect(){
        with(bind){
            sentiment5ConstraintHost.backgroundTintList = ColorStateList.valueOf(color(R.color.white))
            sentiment4ConstraintHost.backgroundTintList = ColorStateList.valueOf(color(R.color.white))
            sentiment3ConstraintHost.backgroundTintList = ColorStateList.valueOf(color(R.color.white))
            sentiment2ConstraintHost.backgroundTintList = ColorStateList.valueOf(color(R.color.white))
            sentiment1ConstraintHost.backgroundTintList = ColorStateList.valueOf(color(R.color.white))
        }
    }
}