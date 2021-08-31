package com.pentatrespassers.neodoollae.view.login.main.friend.friendlist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pentatrespassers.neodoollae.databinding.CellFriendListBinding
import com.pentatrespassers.neodoollae.dto.User

class FriendListAdapter(context: Context, private var userList: List<User>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    inner class FriendListHolder(private val bind: CellFriendListBinding) :
        RecyclerView.ViewHolder(bind.root) {
        fun binding(user: User) {
            with(bind) {
                nicknameTextFriendList.text = user.nickname
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return FriendListHolder(CellFriendListBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = userList[position]
        (holder as FriendListHolder).binding(data)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun refresh(userList :List<User>) {
        this.userList = userList
        notifyDataSetChanged()
    }
}