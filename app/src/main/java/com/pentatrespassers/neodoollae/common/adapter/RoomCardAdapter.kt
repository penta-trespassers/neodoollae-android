package com.pentatrespassers.neodoollae.common.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.databinding.CellRoomCardBinding
import com.pentatrespassers.neodoollae.dto.Room
import com.pentatrespassers.neodoollae.lib.Util.gone
import com.pentatrespassers.neodoollae.lib.Util.hide
import com.pentatrespassers.neodoollae.lib.Util.setImageCornerRadius
import com.pentatrespassers.neodoollae.lib.Util.show
import com.pentatrespassers.neodoollae.view.login.main.home.AddRoomActivity
import com.pentatrespassers.neodoollae.view.login.main.home.RoomProfileActivity
import splitties.activities.start
import splitties.bundle.putExtras
import splitties.resources.appStr
import splitties.resources.str

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
                roomImage.setImageCornerRadius(32F)
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
                    roomRatingImage.gone()
                    roomStatusRoomCard.hide()
                    roomNameTextRoomCard.text = context.str(R.string.home_add_room)
                    nicknameTextRoomCard.text = context.str(R.string.home_enroll_room)

                    itemView.setOnClickListener {
                        context.start<AddRoomActivity>()
                    }
                } else {
                    when (room.roomImages.isNullOrEmpty()) {
                        true -> {
                            roomImage.apply {
                                background = AppCompatResources.getDrawable(
                                    context,
                                    R.drawable.ic_common_bed
                                )
                                updateLayoutParams {
                                    width = 96
                                    height = 96
                                }
                            }
                        }
                        false -> {
                            roomImage.apply {
                                updateLayoutParams {
                                    width = 0
                                    height = 0
                                }
                            }
                            Glide.with(context)
                                .load(room.roomImages?.get(0))
                                .into(roomImage)
                        }
                    }
                    roomRatingImage.show()
                    roomStatusRoomCard.show()
                    roomNameTextRoomCard.text = room.roomName
                    nicknameTextRoomCard.text = room.nickname

                    itemView.setOnClickListener {
                        context.start<RoomProfileActivity> {
                            putExtras(RoomProfileActivity.Extras) {
                                this.room = room
                            }
                        }
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