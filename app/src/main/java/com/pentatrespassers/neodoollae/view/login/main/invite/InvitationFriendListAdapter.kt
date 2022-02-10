package com.pentatrespassers.neodoollae.view.login.main.invite

import android.content.Context
import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.databinding.CellFriendListBinding
import com.pentatrespassers.neodoollae.databinding.CellFriendRequestBinding
import com.pentatrespassers.neodoollae.databinding.CellInvitationFriendBinding
import com.pentatrespassers.neodoollae.dto.FriendRequest
import com.pentatrespassers.neodoollae.dto.User
import com.pentatrespassers.neodoollae.lib.Util.hide

class InvitationFriendListAdapter(
    private val context: Context,
    private var invitationFriendsList: ArrayList<User>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    inner class CellInvitationFriendListHolder(private val bind: CellFriendRequestBinding) :
        RecyclerView.ViewHolder(bind.root) {
        fun binding(friend: User) {
            with(bind) {
                nicknameTextFriendRequest.text = friend.nickname
                acceptButtonFriendRequest.hide()
                declineButtonFriendRequest.setImageResource(R.drawable.ic_check_circle_24dp)


            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CellInvitationFriendListHolder(
            CellFriendRequestBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CellInvitationFriendListHolder).binding(invitationFriendsList[position])
    }

    override fun getItemCount(): Int {
        return invitationFriendsList.size
    }

    fun refresh(invitationFriendsList: ArrayList<User>) {
        this.invitationFriendsList = invitationFriendsList
        notifyDataSetChanged()
    }
}
