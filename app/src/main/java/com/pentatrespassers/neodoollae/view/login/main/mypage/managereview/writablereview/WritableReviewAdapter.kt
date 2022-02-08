package com.pentatrespassers.neodoollae.view.login.main.mypage.managereview.writablereview


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pentatrespassers.neodoollae.databinding.CellWritableReviewBinding
import com.pentatrespassers.neodoollae.dto.Reservation
//import com.pentatrespassers.neodoollae.dto.Review
import com.pentatrespassers.neodoollae.dto.User
import com.pentatrespassers.neodoollae.lib.Authentication
import com.pentatrespassers.neodoollae.view.login.main.friend.friendlist.FriendProfileActivity
import splitties.activities.start
import splitties.bundle.putExtras

class WritableReviewAdapter(private var context: Context, private var reviewList: ArrayList<Reservation>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    inner class CellWritableReviewHolder(private val bind: CellWritableReviewBinding) :
        RecyclerView.ViewHolder(bind.root) {
        fun binding(reservation: Reservation) {
            with(bind) {
//                if (Authentication.user!!.id == reservation.userId) { // 내가 예약한 사람(손님)이라는 거지
//                    reviewRequestText.text = reservation.hostName + "님의 방 리뷰를 작성해주세요."
//                }
//                else { // 내가 예약 받은 사람(주인)이라는 거지
//                    reviewRequestText.text = reservation.nickName + "님의 손님 리뷰를 작성해주세요."
//                }
//
//                notificationTimeTextNotice.text = reservation.createdAt.toString()
//                visitedRoomTextNotice.text = reservation.roomName
//                writeReviewEditTextNotice.hint =  "예) 퇴실할 때 청소까지 해주는 고마운 손님이었어요 :)"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return CellWritableReviewHolder(CellWritableReviewBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = reviewList[position]
//        (holder as ReviewManagementAdapter.CellWritableReviewHolder).binding(data)
    }

    override fun getItemCount(): Int {
        return reviewList.size
    }

    fun refresh(reviewList: ArrayList<Reservation>) {
        this.reviewList = reviewList
        notifyDataSetChanged()
    }

}