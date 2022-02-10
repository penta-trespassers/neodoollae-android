package com.pentatrespassers.neodoollae.view.login.main.invite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pentatrespassers.neodoollae.databinding.ActivityInvitationChooseFriendBinding
import com.pentatrespassers.neodoollae.dto.Reservation

class InvitationChooseFriendActivity : AppCompatActivity(){
    var invitation : Reservation = Reservation()

    private val bind by lazy {
        ActivityInvitationChooseFriendBinding.inflate(layoutInflater)
    }

    lateinit var invitationFriendAdapter: InvitationFriendAdapter
    lateinit var invitationFriendListAdapter: InvitationFriendListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(bind) {
            setContentView(root)

            invitationFriendRecycler.adapter = invitationFriendAdapter
            invitationFriendListRecycler.adapter = invitationFriendListAdapter

            invitationFriendAdapter.notifyDataSetChanged()
            invitationFriendListAdapter.notifyDataSetChanged()

        }
    }





}