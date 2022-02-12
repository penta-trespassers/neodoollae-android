package com.pentatrespassers.neodoollae.view.login.main.home.roomprofile.invitation

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.databinding.CellInvitationFriendListBinding
import com.pentatrespassers.neodoollae.dto.User
import splitties.resources.color

class InvitationFriendListAdapter(
    private val context: Context,
    ) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val layoutInflater: LayoutInflater = LayoutInflater.from(context)
    lateinit var anotherAdapter: InvitationFriendAdapter


    var friendslist: ArrayList<InviteFriend> = arrayListOf()

    inner class CellInvitationFriendListHolder(private val bind: CellInvitationFriendListBinding) :
        RecyclerView.ViewHolder(bind.root) {
        fun binding(friend: InviteFriend) {
            with(bind) {
                nicknameTextFriendRequest.text = friend.user.nickname
                if (!friend.isInvite) {
                    itemView.setBackgroundColor(context.color(R.color.white))
                    acceptButtonFriendRequest.imageTintList = ColorStateList.valueOf(context.color(R.color.grey_200))
                } else {
                    itemView.setBackgroundColor(context.color(R.color.color_card))
                    acceptButtonFriendRequest.imageTintList = ColorStateList.valueOf(context.color(R.color.yellow_600))

                }

                itemView.setOnClickListener {
                    if (friend.isInvite) {
                        itemView.setBackgroundColor(context.color(R.color.white))
                        acceptButtonFriendRequest.imageTintList = ColorStateList.valueOf(context.color(R.color.grey_200))
                        val index = anotherAdapter.invitationFriendsList.indexOf(friend)
                        anotherAdapter.invitationFriendsList.removeAt(index)
                        anotherAdapter.notifyItemRemoved(index)

                    } else {
                        itemView.setBackgroundColor(context.color(R.color.color_card))
                        acceptButtonFriendRequest.imageTintList = ColorStateList.valueOf(context.color(R.color.yellow_600))
                        anotherAdapter.invitationFriendsList.add(friend)
                        anotherAdapter.notifyItemInserted(anotherAdapter.invitationFriendsList.size)

                    }
                    friend.isInvite = !friend.isInvite
                }

            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CellInvitationFriendListHolder(
            CellInvitationFriendListBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CellInvitationFriendListHolder).binding(friendslist[position])
    }

    override fun getItemCount(): Int {
        return friendslist.size
    }

    fun refresh(invitationFriendsList: ArrayList<InviteFriend>) {
        this.friendslist = invitationFriendsList
        notifyDataSetChanged()
    }

    fun init(anotherAdapter: InvitationFriendAdapter) {
        this.anotherAdapter = anotherAdapter
    }

}
