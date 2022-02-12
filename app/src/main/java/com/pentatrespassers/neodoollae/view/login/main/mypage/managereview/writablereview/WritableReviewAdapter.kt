package com.pentatrespassers.neodoollae.view.login.main.mypage.managereview.writablereview


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pentatrespassers.neodoollae.databinding.CellWritableReviewBinding
import com.pentatrespassers.neodoollae.dto.Reservation
import com.pentatrespassers.neodoollae.lib.Authentication


class WritableReviewAdapter(private var context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var reviewList: ArrayList<Reservation> = arrayListOf()
    val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    inner class CellWritableReviewHolder(private val bind: CellWritableReviewBinding) :
        RecyclerView.ViewHolder(bind.root) {
        fun binding(reservation: Reservation) {
            with(bind) {
                if (Authentication.user!!.id == reservation.userId) { // 내가 예약한 사람(손님)
                    reviewRequestText.text = reservation.roomName + "의 방과 주인 리뷰를 작성해주세요."
                }
                else { // 내가 예약 받은 사람(주인)
                    reviewRequestText.text = reservation.nickname + "님의 손님 리뷰를 작성해주세요."
                }
                notificationTimeTextNotice.text = reservation.createdAt.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CellWritableReviewHolder(CellWritableReviewBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = reviewList[position]
        (holder as WritableReviewAdapter.CellWritableReviewHolder).binding(data)
    }

    override fun getItemCount(): Int {
        return reviewList.size
    }

    fun refresh(reviewList: ArrayList<Reservation>) {
        this.reviewList = reviewList
        notifyDataSetChanged()
    }

}