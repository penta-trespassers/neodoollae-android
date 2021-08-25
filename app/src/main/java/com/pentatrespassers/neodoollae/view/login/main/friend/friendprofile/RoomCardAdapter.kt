package com.pentatrespassers.neodoollae.view.login.main.friend.friendprofile

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.databinding.CellRoomCardBinding
import com.pentatrespassers.neodoollae.dto.RoomInfo

class RoomCardAdapter(private val context: Context, private val roomInfoList: ArrayList<RoomInfo>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class RoomCardHolder(private val bind: CellRoomCardBinding) :
        RecyclerView.ViewHolder(bind.root) {
        fun binding(roomInfo: RoomInfo) {
            with(bind) {
                if (roomInfo.status == RoomInfo.STATUS_UNDEFINED) {
                    roomImage.run {
                        setImageResource(R.drawable.ic_plus)
                        scaleType = ImageView.ScaleType.CENTER
                        background = AppCompatResources.getDrawable(context, R.drawable.bg_border_white_black)
                    }
                    roomRatingImage.visibility = View.INVISIBLE
                    roomNameTextRoomCard.visibility = View.INVISIBLE
                    nicknameTextRoomCard.visibility = View.INVISIBLE
                    roomStatusText.visibility = View.INVISIBLE
                } else {
                    roomNameTextRoomCard.text = roomInfo.roomName
                    roomStatusText.text = when (roomInfo.status) {
                        RoomInfo.STATUS_OPEN -> context.getString(R.string.open)
                        RoomInfo.STATUS_CLOSED -> context.getString(R.string.closed)
                        else -> context.getString(R.string.restricted)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        return RoomCardHolder(CellRoomCardBinding.inflate(layoutInflater, parent, false))
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val room = roomInfoList[position]
        (holder as RoomCardHolder).binding(room)
    }

    override fun getItemCount(): Int {
        return roomInfoList.size
    }


}