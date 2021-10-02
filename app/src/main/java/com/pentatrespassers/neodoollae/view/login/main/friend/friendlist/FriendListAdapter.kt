package com.pentatrespassers.neodoollae.view.login.main.friend.friendlist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pentatrespassers.neodoollae.databinding.CellFriendListBinding
import com.pentatrespassers.neodoollae.dto.User
import splitties.activities.start
import splitties.bundle.putExtras

class FriendListAdapter(private var context: Context, private var userList: ArrayList<User>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    inner class CellFriendListHolder(private val bind: CellFriendListBinding) :
        RecyclerView.ViewHolder(bind.root) {
        fun binding(user: User) {
            with(bind) {
                nicknameTextFriendList.text = user.nickname
                itemView.setOnClickListener {
                    context.start<FriendProfileActivity> {
                        putExtras(FriendProfileActivity.Extras) {
                            this.user = user
                        }
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return CellFriendListHolder(CellFriendListBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = userList[position]
        (holder as CellFriendListHolder).binding(data)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun refresh(userList: ArrayList<User>) {
        this.userList = userList
        notifyDataSetChanged()
    }
}