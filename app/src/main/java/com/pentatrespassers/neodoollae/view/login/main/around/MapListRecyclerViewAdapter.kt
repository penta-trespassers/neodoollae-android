package com.pentatrespassers.neodoollae.view.login.main.around

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.databinding.CellMaplistitemBinding
import com.pentatrespassers.neodoollae.dto.RoomInfo

class MapListRecyclerViewAdapter(private val context: Context, private val mapItemList: List<RoomInfo>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class MapListItemHolder(private val bind: CellMaplistitemBinding) :
        RecyclerView.ViewHolder(bind.root) {
        fun binding(roomData: RoomInfo) {
            with(bind) {
                roomTitleTextViewAround.text = roomData.roomName
                roomHostNametextViewAround.text = roomData.nickname
                roomRateImageView.setImageResource(R.drawable.ic_sentiment_very_satisfied_black_24dp)
                roomImageView1.setImageResource(R.drawable.app_icon)
                roomImageView2.setImageResource(R.drawable.app_icon)
                roomImageView3.setImageResource(R.drawable.app_icon)
                roomConditionTextView.text = "opened"
                distanceTextView.text= "300m"
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
        val roomdata = mapItemList[position]
        (holder as MapListItemHolder).binding(roomdata)
    }

    override fun getItemCount(): Int {
        return mapItemList.size
    }


    }