package com.pentatrespassers.neodoollae.view.login.main.home.roomprofile.invitation

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.databinding.CellFriendRequestBinding
import com.pentatrespassers.neodoollae.dto.User
import com.pentatrespassers.neodoollae.lib.Util.hide
import splitties.resources.color

class InvitationFriendListAdapter(
    private val context: Context,
    private var friendslist: ArrayList<User>
    ) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    inner class CellInvitationFriendListHolder(private val bind: CellFriendRequestBinding) :
        RecyclerView.ViewHolder(bind.root) {
        fun binding(friend: User) {
            with(bind) {
                nicknameTextFriendRequest.text = friend.nickname
                acceptButtonFriendRequest.hide()
                declineButtonFriendRequest.setImageResource(R.drawable.ic_check_circle_24dp)
                declineButtonFriendRequest.setBackgroundColor(context.color(R.color.blue_grey_600))
                itemView.setOnClickListener {
                   declineButtonFriendRequest.setBackgroundColor(context.color(R.color.yellow_600))
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CellInvitationFriendListHolder(
            CellFriendRequestBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CellInvitationFriendListHolder).binding(friendslist[position])
    }

    override fun getItemCount(): Int {
        return friendslist.size
    }

    fun refresh(invitationFriendsList: ArrayList<User>) {
        this.friendslist = invitationFriendsList
        notifyDataSetChanged()
    }
}
