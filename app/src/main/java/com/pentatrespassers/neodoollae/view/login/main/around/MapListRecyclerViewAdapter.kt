package com.pentatrespassers.neodoollae.view.login.main.around

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat.setImageTintList
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.databinding.CellMaplistitemBinding
import com.pentatrespassers.neodoollae.dto.Room
import com.pentatrespassers.neodoollae.view.login.main.home.RoomProfileActivity
import com.pentatrespassers.neodoollae.view.login.main.home.roomactivity.RoomImageAdapter
import splitties.activities.start
import splitties.bundle.putExtras
import kotlin.math.*

class MapListRecyclerViewAdapter(
    private val context: Context,
    private val mapItemList: List<Room>,
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {

    var filterList = mapItemList

    inner class MapListItemHolder(private val bind: CellMaplistitemBinding) :
        RecyclerView.ViewHolder(bind.root) {
        fun binding(roomData: Room) {
            with(bind) {
                roomTitleTextViewAround.text = roomData.roomName
                roomHostNametextViewAround.text = roomData.nickname
                roomRateImageView.setImageResource(R.drawable.ic_sentiment_very_satisfied)

                // set distance



                distanceTextView.text = getDistance(
                    roomData.latitude,
                    roomData.longitude,
                    37.5670135, 126.9783740
                )
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
            CellMaplistitemBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MapListItemHolder).binding(filterList[position])
    }

    override fun getItemCount(): Int {
        return filterList.size
    }

    // 데이터 필터 검색 Filterable implement ---------------------------------
    override fun getFilter(): Filter? {
        return exampleFilter
    }

    private val exampleFilter: Filter = object : Filter() {
        //Automatic on background thread
        override fun performFiltering(constraint: CharSequence): FilterResults {
            val filterResult = ArrayList<Room>()


            print("안녕 필터")

            if (constraint.isEmpty()) {
                filterList = mapItemList
            } else {
                val filterPattern = constraint.toString()

                for (item in mapItemList) {
                    if (item.roomName?.contains(filterPattern) == true) {
                        print("나는 써니")
                        filterResult.add(item)
                    }
                }
                filterList = filterResult
            }
            val results = FilterResults()
            results.values = filterList
            return results
        }

        //Automatic on UI thread
        override fun publishResults(constraint: CharSequence, results: FilterResults) {
            if (constraint == null) {
                filterList = mapItemList
            } else {
                filterList = results?.values as ArrayList<Room>
            }
            notifyDataSetChanged()
        }
    }

    /**
     * 두 좌표의 거리를 계산한다.
     *
     * @param lat1 위도1
     * @param lon1 경도1
     * @param lat2 위도2
     * @param lon2 경도2
     * @return 두 좌표의 거리(m)
     */
    fun getDistance(lat1: Double, lon1: Double, lat2: Double, lon2: Double): String {
        val r = 6372.8 * 1000
        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)
        val a = sin(dLat / 2).pow(2.0) + sin(dLon / 2).pow(2.0) * cos(Math.toRadians(lat1)) * cos(Math.toRadians(lat2))
        val c = 2 * asin(sqrt(a))
        return (r * c).toInt().toString() + "m"
    }
}