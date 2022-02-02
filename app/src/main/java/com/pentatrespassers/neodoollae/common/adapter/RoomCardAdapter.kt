package com.pentatrespassers.neodoollae.common.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.databinding.CellRoomCardBinding
import com.pentatrespassers.neodoollae.dto.Room
import com.pentatrespassers.neodoollae.lib.Util.hide

class RoomCardAdapter(
    private val context: Context,
    private val roomList: ArrayList<Room>,
    private val functionsOnBind: ArrayList<(adapter: RoomCardAdapter) -> Unit> = arrayListOf()
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    init {
        functionsOnBind.forEach {
            it(this)
        }
    }


    inner class RoomCardHolder(private val bind: CellRoomCardBinding) :
        RecyclerView.ViewHolder(bind.root) {
        fun binding(room: Room) {
            with(bind) {
                if (room.status == Room.STATUS_UNDEFINED) {
                    roomImage.apply {
                        background = AppCompatResources.getDrawable(
                            context,
                            R.drawable.ic_common_add
                        )
                        updateLayoutParams {
                            width = 96
                            height = 96
                        }
                    }
                    roomRatingImage.hide()
                    roomNameTextRoomCard.hide()
                    nicknameTextRoomCard.hide()
                } else {
                    roomNameTextRoomCard.text = room.roomName
                    nicknameTextRoomCard.text = room.nickname
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(context)

        return RoomCardHolder(CellRoomCardBinding.inflate(layoutInflater, parent, false))
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val room = roomList[position]
        (holder as RoomCardHolder).binding(room)

    }

    override fun getItemCount(): Int {
        return roomList.size
    }

    fun refresh(newRooms: List<Room>? = null) {
        newRooms?.let {
            roomList.clear()
            roomList.addAll(newRooms)
        }
        notifyDataSetChanged()

        functionsOnBind.forEach {
            it(this)
        }
    }

    fun addFunctionOnBind(func: (adapter: RoomCardAdapter) -> Unit) {
        functionsOnBind.add(func)
    }


}