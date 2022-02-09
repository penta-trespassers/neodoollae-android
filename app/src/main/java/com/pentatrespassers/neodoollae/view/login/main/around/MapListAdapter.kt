package com.pentatrespassers.neodoollae.view.login.main.around

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.databinding.CellMapListBinding
import com.pentatrespassers.neodoollae.dto.Room

class MapListAdapter(
    private val context: Context,
    private val mapItemList: List<Room>
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class MapListItemHolder(private val bind: CellMapListBinding) :
        RecyclerView.ViewHolder(bind.root) {
        fun binding(roomData: Room) {
            with(bind) {
                roomTitleTextViewAround.text = roomData.roomName
                roomHostNametextViewAround.text = roomData.nickname
                roomRateImageView.setImageResource(R.drawable.ic_sentiment_very_satisfied)

                distanceTextView.text = "300m"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        return MapListItemHolder(
            CellMapListBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MapListItemHolder).binding(mapItemList[position])
    }

    override fun getItemCount(): Int {
        return mapItemList.size
    }


}