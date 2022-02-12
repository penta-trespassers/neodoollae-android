package com.pentatrespassers.neodoollae.view.login.main.friend.friendlist.friendprofile.review


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.databinding.CellReviewBinding
import com.pentatrespassers.neodoollae.dto.Reservation
import com.pentatrespassers.neodoollae.lib.Util
import com.pentatrespassers.neodoollae.view.login.main.friend.friendlist.friendprofile.ReviewActivity
import splitties.toast.toast


class ReviewAdapter(
    context: Context, private var reviewList: ArrayList<Reservation>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    val formatter1 = Util.getDateFormatter("yyyy.MM.dd")
    val formatter2 = Util.getDateFormatter("a hh:mm")

    inner class CellReviewHolder(private val bind: CellReviewBinding) :
        RecyclerView.ViewHolder(bind.root) {
        fun binding(reservation: Reservation) {
            with(bind) {
                friendNameReviewCell.text = reservation.nickname
                dateReviewCell.text = formatter1.format(reservation.checkOut) + " " + formatter2.format(reservation.checkOut)
                guestReviewTextView.text = reservation.requestMessage // 일단 임시로 이걸로 해놨어요

                /** 이 부분 구현 덜 되었어요 - 추후 논의 필요 **/
                val pop = PopupMenu(itemView.context, itemView)
                pop.inflate(R.menu.review_menu)
                pop.menu.getItem(0).apply {
                    title = "리뷰 신고"
                    setOnMenuItemClickListener {
                        toast("신고 접수되었습니다.")
                        true
                    }
                }
                pop.menu.getItem(1).apply {
                    title = "리뷰 삭제"
                    setOnMenuItemClickListener {
                        toast("삭제되었습니다.")
                        true
                    }
                }
                itemView.setOnClickListener {
                    pop.show()
                    true
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CellReviewHolder(CellReviewBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = reviewList[position]
        (holder as ReviewAdapter.CellReviewHolder).binding(data)
    }

    override fun getItemCount(): Int {
        return reviewList.size
    }

    fun refresh(reviewList: ArrayList<Reservation>) {
        this.reviewList = reviewList
        notifyDataSetChanged()
    }
}