package com.pentatrespassers.neodoollae.view.login.main.home.roomactivity

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.databinding.CellRoomImageBinding
import com.pentatrespassers.neodoollae.view.login.main.mypage.ShowImageActivity
import splitties.activities.start
import splitties.bundle.putExtras


class RoomImageAdapter(private val context: Context, private var imageList: List<String>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class RoomImageHolder(private val bind: CellRoomImageBinding) :
        RecyclerView.ViewHolder(bind.root) {
        fun binding(image: String) {
            with(bind) {
                Glide.with(context)
                    .load(image)
                    .error(R.drawable.ic_common_bed)
                    .into(roomImage)

                itemView.setOnClickListener {
                    context.start<ShowImageActivity> {
                        putExtras(ShowImageActivity.Extras) {
                            this.profileImage = image
                        }
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        return RoomImageHolder(CellRoomImageBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as RoomImageHolder).binding(imageList[position])
    }

    override fun getItemCount(): Int {
        return imageList.size
    }
}