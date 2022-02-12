package com.pentatrespassers.neodoollae.view.login.main.home.roomprofile.invitation

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pentatrespassers.neodoollae.databinding.CellInvitationFriendBinding
import com.pentatrespassers.neodoollae.dto.User


class InvitationFriendAdapter(
    private val context: Context,

) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var invitationFriendsList: ArrayList<User> = arrayListOf()
    val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    inner class CellInvitationFriendHolder(private val bind: CellInvitationFriendBinding) :
        RecyclerView.ViewHolder(bind.root) {
        fun binding(friend: User) {
            with(bind) {

                invitationFriendNameText.text = friend.nickname
                invitationCancelButton.setOnClickListener {
                   invitationFriendsList.remove(friend)
                   notifyDataSetChanged()
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CellInvitationFriendHolder(
            CellInvitationFriendBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CellInvitationFriendHolder).binding(invitationFriendsList[position])
    }

    override fun getItemCount(): Int {
        return invitationFriendsList.size
    }

    fun refresh(invitationFriendsList: ArrayList<User>) {
        this.invitationFriendsList = invitationFriendsList
        notifyDataSetChanged()
    }
}