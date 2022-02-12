package com.pentatrespassers.neodoollae.view.login.main.around

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat.setImageTintList
import androidx.recyclerview.widget.RecyclerView
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.databinding.CellMapListBinding
import com.pentatrespassers.neodoollae.dto.Room
import com.pentatrespassers.neodoollae.view.login.main.home.RoomProfileActivity
import com.pentatrespassers.neodoollae.view.login.main.home.roomprofile.RoomImageAdapter
import splitties.activities.start
import splitties.bundle.putExtras

class MapListAdapter(
    private val context: Context
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {

    var mapItemList = arrayListOf<Room>()

    private var filteredList = mapItemList

    inner class MapListItemHolder(private val bind: CellMapListBinding) :
        RecyclerView.ViewHolder(bind.root) {
        fun binding(roomData: Room) {
            with(bind) {
                roomTitleTextViewAround.text = roomData.roomName
                roomHostNametextViewAround.text = roomData.nickname
                roomRateImageView.setImageResource(R.drawable.ic_sentiment_very_satisfied)

                // set distance
//                distanceTextView.text = getDistance(
//                    roomData.latitude,
//                    roomData.longitude
//                )
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

                itemView.setOnClickListener {
                    context.start<RoomProfileActivity> {
                        putExtras(RoomProfileActivity.Extras) {
                            this.room = roomData
                        }
                    }
                }
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
        (holder as MapListItemHolder).binding(filteredList[position])
    }

    override fun getItemCount(): Int {
        return filteredList.size
    }

    var lastConstraint = ""

    fun refresh() {
        filter.filter(lastConstraint)
    }

    fun refresh(mapItemList: ArrayList<Room>) {
        this.mapItemList = mapItemList
        refresh()
    }

    override fun getFilter() = object : Filter() {
        override fun performFiltering(constraint: CharSequence): FilterResults {
            lastConstraint = constraint.toString()
            filteredList = if (lastConstraint.isEmpty()) {
                mapItemList
            } else {
                val filteredList = ArrayList<Room>()
                for (room in mapItemList) {
                    if (room.roomName.lowercase()
                            .contains(lastConstraint.lowercase())
                    ) {
                        filteredList.add(room);
                    }
                }
                filteredList
            }
            return FilterResults()
        }

        override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
            notifyDataSetChanged()
        }
    }
}
