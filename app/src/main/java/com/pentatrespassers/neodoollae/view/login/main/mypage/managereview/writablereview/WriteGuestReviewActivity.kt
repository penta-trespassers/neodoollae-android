package com.pentatrespassers.neodoollae.view.login.main.mypage.managereview.writablereview

import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.databinding.ActivityWriteGuestReviewBinding
import com.pentatrespassers.neodoollae.dto.Reservation
import com.pentatrespassers.neodoollae.lib.Util
import com.pentatrespassers.neodoollae.view.login.main.reservation.ReservationProfileActivity
import splitties.bundle.BundleSpec
import splitties.bundle.bundle
import splitties.bundle.withExtras
import splitties.resources.color
import splitties.toast.toast

class WriteGuestReviewActivity : AppCompatActivity() {

    private val bind by lazy {
        ActivityWriteGuestReviewBinding.inflate(layoutInflater)
    }

    var guestScore = 5


    object Extras : BundleSpec() {
        var reviews: Reservation by bundle()
    }

    private val guestReview by lazy {
        withExtras(Extras) {
            reviews
        }
    }


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


            val formatter1 = Util.getDateFormatter("yyyy.MM.dd")
            val formatter2 = Util.getDateFormatter("a hh:mm")

            guestNameText.text = guestReview.nickname
            roomNameText.text = guestReview.roomName
            checkInDateText.text = formatter1.format(guestReview.checkIn)
            checkInTimeText.text = formatter2.format(guestReview.checkIn)
            checkOutDateText.text = formatter1.format(guestReview.checkOut)
            checkOutTimeText.text = formatter2.format(guestReview.checkOut)


            guestReviewSkipButton.setOnClickListener {
                toast("손님 리뷰를 건너뛰었습니다.")
                finish()
            }

            guestReviewAddButton.setOnClickListener {
                toast("손님 리뷰 작성 완료!")
                finish()
            }

            backButtonWriteGuestReview.setOnClickListener {
                onBackPressed()
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