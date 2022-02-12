package com.pentatrespassers.neodoollae.view.login.main.mypage.managereview.writablereview

import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.databinding.ActivityWriteGuestReviewBinding
import splitties.resources.color

class WriteGuestReviewActivity : AppCompatActivity() {

    private val bind by lazy {
        ActivityWriteGuestReviewBinding.inflate(layoutInflater)
    }

    var guestScore = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(bind){
            setContentView(root)

            sentiment5Constraint.setOnClickListener {
                guestScore = 5
                makeUnselect()
                it.backgroundTintList = ColorStateList.valueOf(color(R.color.color_card_pressed))
            }

            sentiment4Constraint.setOnClickListener {
                guestScore = 4
                makeUnselect()
                it.backgroundTintList = ColorStateList.valueOf(color(R.color.color_card_pressed))
            }

            sentiment3Constraint.setOnClickListener {
                guestScore = 3
                makeUnselect()
                it.backgroundTintList = ColorStateList.valueOf(color(R.color.color_card_pressed))
            }

            sentiment2Constraint.setOnClickListener {
                guestScore = 2
                makeUnselect()
                it.backgroundTintList = ColorStateList.valueOf(color(R.color.color_card_pressed))
            }

            sentiment1Constraint.setOnClickListener {
                guestScore = 1
                makeUnselect()
                it.backgroundTintList = ColorStateList.valueOf(color(R.color.color_card_pressed))
            }






        }
    }

    private fun makeUnselect(){
        with(bind){
            sentiment5Constraint.backgroundTintList = ColorStateList.valueOf(color(R.color.white))
            sentiment4Constraint.backgroundTintList = ColorStateList.valueOf(color(R.color.white))
            sentiment3Constraint.backgroundTintList = ColorStateList.valueOf(color(R.color.white))
            sentiment2Constraint.backgroundTintList = ColorStateList.valueOf(color(R.color.white))
            sentiment1Constraint.backgroundTintList = ColorStateList.valueOf(color(R.color.white))
        }
    }
}