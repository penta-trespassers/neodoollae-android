package com.pentatrespassers.neodoollae.view.login.main.home.roomprofile.invitation

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pentatrespassers.neodoollae.databinding.CellInvitationFriendBinding


class InvitationFriendAdapter(
    private val context: Context,

) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var invitationFriendsList: ArrayList<InviteFriend> = arrayListOf()
    val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    lateinit var anotherAdapter: InvitationFriendListAdapter

    inner class CellInvitationFriendHolder(private val bind: CellInvitationFriendBinding) :
        RecyclerView.ViewHolder(bind.root) {
        fun binding(friend: InviteFriend) {
            with(bind) {
                invitationFriendNameText.text = friend.user.nickname

                invitationCancelButton.setOnClickListener {


                    friend.isInvite = false
                    anotherAdapter.refresh()

                    val myIndex = invitationFriendsList.indexOf(friend)
                    invitationFriendsList.removeAt(myIndex)
                    notifyItemRemoved(myIndex)
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

    fun refresh(invitationFriendsList: ArrayList<InviteFriend>) {
        this.invitationFriendsList = invitationFriendsList
        notifyDataSetChanged()
    }

    fun init(anotherAdapter: InvitationFriendListAdapter) {
        this.anotherAdapter = anotherAdapter
    }
}