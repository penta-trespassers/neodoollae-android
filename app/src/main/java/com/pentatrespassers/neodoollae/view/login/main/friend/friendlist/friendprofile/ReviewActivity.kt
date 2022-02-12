package com.pentatrespassers.neodoollae.view.login.main.friend.friendlist.friendprofile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pentatrespassers.neodoollae.databinding.ActivityReviewBinding
import com.pentatrespassers.neodoollae.dto.Reservation
import com.pentatrespassers.neodoollae.view.login.main.friend.friendlist.friendprofile.review.ReviewAdapter
import splitties.bundle.BundleSpec
import splitties.bundle.bundle
import splitties.bundle.withExtras
import java.sql.Timestamp
import java.util.*


class ReviewActivity : AppCompatActivity() {

    lateinit var reviewAdapter: ReviewAdapter

    object Extras : BundleSpec() {
        var reviews: Reservation by bundle()
    }


    private val review by lazy {
        withExtras(Extras) {
            reviews
        }
    }

    val time = Timestamp(System.currentTimeMillis())
    val time2 = Timestamp(Calendar.getInstance().apply {
        set(2022, 2, 12)
    }.timeInMillis)

    private val reviewList = arrayListOf(
        Reservation(
            1,
            2,
            1,
            "가은",
            "seojin",
            "서진의 방",
            time2,
            time,
            null,
            "치킨 맛있어요!! 복층 좋아요!! 난방도 따뜻하게 잘 되구 아침에 테라스에서 커피 한 잔 하는 게 낭만적이었어요~",
            "샘플 response데이터입니다"
        ),
        Reservation(
            2,
            2,
            1,
            "에오스",
            "sunny",
            "써니의 방",
            time2,
            time,
            null,
            "아기자기 예쁜 방이에요 인스타 감성 짱! 인테리어 너무 예쁘고 빔프로젝터가 최고였어요",
            "샘플 response데이터입니다"
        ),
        Reservation(
            3,
            1,
            1,
            "건녀",
            "inticoy",
            "비밀의 방",
            time2,
            time,
            null,
            "비밀의 방 최고 ^_^! 학교에서 출발하면 좀 멀지만 우리집에서는 가까워서 좋았어요. ",
            "샘플 response데이터입니다"
        ),
        Reservation(
            4,
            1,
            1,
            "나",
            "recaster",
            "진하의 방",
            time2,
            time,
            null,
            "멀어서 가기 힘들었지만 넓고 쾌적했어요!! 다음에도 놀러갈게요~",
            "샘플 response데이터입니다"
        )
    )


    private val bind by lazy {
        ActivityReviewBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(bind) {
            reviewAdapter = ReviewAdapter(root.context, reviewList)
            recyclerViewReview.adapter = reviewAdapter

            textView7.text = review.nickname
            reviewScoreText.text = "손님 리뷰 점수"
            reviewAverageText.text = "4.3점"
            reviewNumText.text = "총 4개"
            backButtonReview.setOnClickListener {
                onBackPressed()
            }


            setContentView(root)
        }
    }
}