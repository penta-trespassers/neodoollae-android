package com.pentatrespassers.neodoollae.view.login.main.mypage.managereview.writtenreview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pentatrespassers.neodoollae.databinding.CellReviewBinding
import com.pentatrespassers.neodoollae.dto.Reservation
import com.pentatrespassers.neodoollae.lib.Util
import com.pentatrespassers.neodoollae.view.login.main.friend.friendlist.friendprofile.ReviewActivity
import com.pentatrespassers.neodoollae.view.login.main.mypage.managereview.WrittenReviewFragment
import splitties.activities.start
import splitties.bundle.putExtras


class WrittenReviewAdapter(
    context: Context, private var reviewList: ArrayList<Reservation>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    val formatter1 = Util.getDateFormatter("yyyy.MM.dd")
    val formatter2 = Util.getDateFormatter("a hh:mm")

    inner class CellWrittenReviewHolder(private val bind: CellReviewBinding) :
        RecyclerView.ViewHolder(bind.root) {
        fun binding(reservation : Reservation) {
            with(bind) {
                friendNameReviewCell.text = reservation.nickname
                dateReviewCell.text = formatter1.format(reservation.checkOut) + " "  + formatter2.format(reservation.checkOut)
                guestReviewTextView.text = reservation.requestMessage // 일단 임시로 이걸로 해놨어요

                itemView.setOnClickListener {
                    root.context.start<ReviewActivity> {
                        putExtras(ReviewActivity.Extras) {
                            this.reviews = reservation
                        }
                    }                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CellWrittenReviewHolder(CellReviewBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = reviewList[position]
        (holder as WrittenReviewAdapter.CellWrittenReviewHolder).binding(data)
    }

    override fun getItemCount(): Int {
        return reviewList.size
    }

    fun refresh(reviewList: ArrayList<Reservation>) {
        this.reviewList = reviewList
        notifyDataSetChanged()
    }

}