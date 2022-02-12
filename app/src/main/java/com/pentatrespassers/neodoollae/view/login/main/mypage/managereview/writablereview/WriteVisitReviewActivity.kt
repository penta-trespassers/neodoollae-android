package com.pentatrespassers.neodoollae.view.login.main.mypage.managereview.writablereview

import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.databinding.ActivityWriteVisitReviewBinding
import com.pentatrespassers.neodoollae.dto.Reservation
import com.pentatrespassers.neodoollae.lib.Util
import splitties.bundle.BundleSpec
import splitties.bundle.bundle
import splitties.bundle.withExtras
import splitties.resources.color
import splitties.toast.toast

class WriteVisitReviewActivity : AppCompatActivity() {

    private val bind by lazy {
        ActivityWriteVisitReviewBinding.inflate(layoutInflater)
    }

    var roomScore = 5
    var hostScore = 5


    object Extras : BundleSpec() {
        var reviews: Reservation by bundle()
    }

    private val visitReview by lazy {
        withExtras(Extras) {
            reviews
        }
    }

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


            val formatter1 = Util.getDateFormatter("yyyy.MM.dd")
            val formatter2 = Util.getDateFormatter("a hh:mm")

            guestNameText.text = visitReview.nickname
            roomNameText.text = visitReview.roomName
            checkInDateText.text = formatter1.format(visitReview.checkIn)
            checkInTimeText.text = formatter2.format(visitReview.checkIn)
            checkOutDateText.text = formatter1.format(visitReview.checkOut)
            checkOutTimeText.text = formatter2.format(visitReview.checkOut)


            visitReviewSkipButton.setOnClickListener {
                toast("방문 리뷰를 건너뛰었습니다.")
                finish()
            }

            visitReviewAddButton.setOnClickListener {
                toast("방문 리뷰 작성 완료!")
                finish()
            }

            backButtonWriteVisitReview.setOnClickListener {
                onBackPressed()
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