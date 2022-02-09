package com.pentatrespassers.neodoollae.view.login.main.friend.friendrequest

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pentatrespassers.neodoollae.databinding.CellFriendRequestBinding
import com.pentatrespassers.neodoollae.dto.FriendRequest
import com.pentatrespassers.neodoollae.network.RetrofitClient

class FriendRequestAdapter(
    private val context: Context,
    private var friendRequestList: ArrayList<FriendRequest>
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    inner class CellFriendRequestHolder(private val bind: CellFriendRequestBinding) :
        RecyclerView.ViewHolder(bind.root) {
        fun binding(friendRequest: FriendRequest) {
            with(bind) {
                nicknameTextFriendRequest.text = friendRequest.friend.nickname
                acceptButtonFriendRequest.setOnClickListener {
                    RetrofitClient.approveFriendRequest(friendRequest.id, true) { _, _ ->
                        friendRequestList.remove(friendRequest)
                        notifyDataSetChanged()
                    }
                }
                declineButtonFriendRequest.setOnClickListener {
                    RetrofitClient.approveFriendRequest(friendRequest.id, false) { _, _ ->
                        friendRequestList.remove(friendRequest)
                        notifyDataSetChanged()
                    }
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CellFriendRequestHolder(
            CellFriendRequestBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CellFriendRequestHolder).binding(friendRequestList[position])
    }

    override fun getItemCount(): Int {
        return friendRequestList.size
    }

    fun refresh(friendRequestList: ArrayList<FriendRequest>) {
        this.friendRequestList = friendRequestList
        notifyDataSetChanged()
    }
}