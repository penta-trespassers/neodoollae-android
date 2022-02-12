package com.pentatrespassers.neodoollae.view.login.main.mypage.managereview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pentatrespassers.neodoollae.databinding.FragmentWritableReviewBinding
import com.pentatrespassers.neodoollae.databinding.FragmentWrittenReviewBinding
import com.pentatrespassers.neodoollae.dto.Notification
import com.pentatrespassers.neodoollae.dto.Reservation
import com.pentatrespassers.neodoollae.view.login.main.mypage.managereview.writablereview.WritableReviewAdapter
import com.pentatrespassers.neodoollae.view.login.main.mypage.managereview.writtenreview.WrittenReviewAdapter
import com.pentatrespassers.neodoollae.view.login.main.notification.NotificationAdapter
import java.sql.Time
import java.sql.Timestamp
import java.util.*
import kotlin.collections.ArrayList

class WrittenReviewFragment constructor() : Fragment() {

    private lateinit var bind: FragmentWrittenReviewBinding

    lateinit var writtenReviewAdapter: WrittenReviewAdapter

//    val time = Timestamp(Calendar.getInstance().apply {
//        set(2022, 2, 12)
//    }.timeInMillis)
    val time = Timestamp(System.currentTimeMillis())
    val time2 = Timestamp(Calendar.getInstance().apply {
        set(2022, 2, 12)
    }.timeInMillis)

    private val writtenReviewList = arrayListOf(
        Reservation(
            1,
            2,
            1,
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
            "sunny",
            "써니의 방",
            time2,
            time,
            null,
            "아기자기 예쁜 방이에요 인스타 감성 짱! 인테리어 너무 예쁘고 특히 빔프로젝터가 최고였어요",
            "샘플 response데이터입니다"
        ),
        Reservation(
            3,
            1,
            1,
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
            "recaster",
            "진하의 방",
            time2,
            time,
            null,
            "멀어서 가기 힘들었지만 넓고 쾌적했어요!! 다음에도 놀러갈게요~ 가본 적도 없는 방 리뷰를 적으려니 힘드네요",
            "샘플 response데이터입니다"
        )
    )



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentWrittenReviewBinding.inflate(inflater, container, false)
        with(bind) {
            writtenReviewAdapter = WrittenReviewAdapter(requireContext(), writtenReviewList)
            writtenReviewRecycler.adapter = writtenReviewAdapter

            return root
        }
    }

    companion object {
        fun newInstance() = WrittenReviewFragment().apply {
        }
    }

    fun refreshWrittenReview() {

    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (hidden) {
            refreshWrittenReview()
        }
    }


}