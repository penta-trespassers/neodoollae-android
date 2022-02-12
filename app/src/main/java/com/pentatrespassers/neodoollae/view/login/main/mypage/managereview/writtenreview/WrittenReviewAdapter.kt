package com.pentatrespassers.neodoollae.view.login.main.mypage.managereview.writtenreview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pentatrespassers.neodoollae.databinding.CellWrittenReviewBinding
import com.pentatrespassers.neodoollae.dto.Reservation


class WrittenReviewAdapter(private var context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var reviewList: ArrayList<Reservation> = arrayListOf()
    val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    inner class CellWrittenReviewHolder(private val bind: CellWrittenReviewBinding) :
        RecyclerView.ViewHolder(bind.root) {
        fun binding(reservation : Reservation) {
            with(bind) {

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CellWrittenReviewHolder(CellWrittenReviewBinding.inflate(layoutInflater, parent, false))
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