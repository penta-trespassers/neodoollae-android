package com.pentatrespassers.neodoollae.view.login.main.around

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.core.widget.ImageViewCompat.setImageTintList
import androidx.recyclerview.widget.RecyclerView
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.databinding.CellMaplistitemBinding
import com.pentatrespassers.neodoollae.dto.Room
import com.pentatrespassers.neodoollae.view.login.main.home.roomactivity.RoomImageAdapter

class MapListRecyclerViewAdapter(
    private val context: Context,
    private val mapItemList: List<Room>
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class MapListItemHolder(private val bind: CellMaplistitemBinding) :
        RecyclerView.ViewHolder(bind.root) {
        fun binding(roomData: Room) {
            with(bind) {
                roomTitleTextViewAround.text = roomData.roomName
                roomHostNametextViewAround.text = roomData.nickname
                roomRateImageView.setImageResource(R.drawable.ic_sentiment_very_satisfied)
                distanceTextView.text = "300m"
                when (roomData.status) {
                    0, 1 -> {
                        roomConditionImageView.setImageResource(R.drawable.ic_common_room_open)
                        setImageTintList(
                            roomConditionImageView,
                            ColorStateList.valueOf(
                                ContextCompat.getColor(
                                    context,
                                    R.color.trespassBlue_900
                                )
                            )
                        )
                    }
                    2 -> {
                        roomConditionImageView.setImageResource(R.drawable.ic_common_room_close)
                        setImageTintList(
                            roomConditionImageView,
                            ColorStateList.valueOf(
                                ContextCompat.getColor(
                                    context,
                                    R.color.trespassRed_900
                                )
                            )
                        )
                    }

                }

                when (roomData.roomImages.isNullOrEmpty()) {
                    true -> {
                        roomCardRecyclerMapListItem.adapter = RoomImageAdapter(
                            context, listOf("no_image")
                        )
                    }
                    false -> {
                        roomCardRecyclerMapListItem.adapter = RoomImageAdapter(
                            context, roomData.roomImages!!
                        )
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        return MapListItemHolder(
            CellMaplistitemBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MapListItemHolder).binding(mapItemList[position])
    }

    override fun getItemCount(): Int {
        return mapItemList.size
    }


}