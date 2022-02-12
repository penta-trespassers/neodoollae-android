package com.pentatrespassers.neodoollae.view.login.main.mypage.managereview.writablereview


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pentatrespassers.neodoollae.databinding.CellWritableReviewBinding
import com.pentatrespassers.neodoollae.dto.Reservation
import com.pentatrespassers.neodoollae.lib.Authentication
import com.pentatrespassers.neodoollae.lib.Util
import com.pentatrespassers.neodoollae.view.login.main.reservation.ReservationProfileActivity
import splitties.activities.start
import splitties.bundle.putExtras


class WritableReviewAdapter(private var context: Context, private var reviewList: ArrayList<Reservation>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

//    var reviewList: ArrayList<Reservation> = arrayListOf()
    val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    val formatter1 = Util.getDateFormatter("yyyy.MM.dd")
    val formatter2 = Util.getDateFormatter("a hh:mm")

    inner class CellWritableReviewHolder(private val bind: CellWritableReviewBinding) :
        RecyclerView.ViewHolder(bind.root) {
        fun binding(reservation: Reservation) {
            with(bind) {
                if (Authentication.user!!.id == reservation.userId) { // 내가 예약한 사람(손님)
                    reviewRequestText.text = reservation.roomName + "의 방과 주인 리뷰를 작성해주세요."
                }
                else if (reservation.userId == 1) { // 더미데이터 적용시키려고 임시로 넣어둔 거
                    reviewRequestText.text = reservation.roomName + "의 방과 주인 리뷰를 작성해주세요."
                }
                else { // 내가 예약 받은 사람(주인)
                    reviewRequestText.text = reservation.nickname + "님의 손님 리뷰를 작성해주세요."
                }
                notificationTimeTextNotice.text = formatter1.format(reservation.checkOut) + " "  + formatter2.format(reservation.checkOut)

                itemView.setOnClickListener {
                    if (Authentication.user!!.id == reservation.userId) { // 내가 손님
                        context.start<WriteVisitReviewActivity> {
                            putExtras(WriteVisitReviewActivity.Extras) {
                                this.reviews = reservation
                            }
                        }
                    }
                    else if (reservation.userId == 1) { // 더미데이터용
                        context.start<WriteVisitReviewActivity> {
                            putExtras(WriteVisitReviewActivity.Extras) {
                                this.reviews = reservation
                            }
                        }
                    }
                    else { // 내가 주인
                        context.start<WriteGuestReviewActivity> {
                            putExtras(WriteGuestReviewActivity.Extras) {
                                this.reviews = reservation
                            }
                        }
                    }
                }
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