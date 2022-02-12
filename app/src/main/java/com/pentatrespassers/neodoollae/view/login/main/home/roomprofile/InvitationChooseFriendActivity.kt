package com.pentatrespassers.neodoollae.view.login.main.home.roomprofile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pentatrespassers.neodoollae.databinding.ActivityInvitationChooseFriendBinding
import com.pentatrespassers.neodoollae.dto.Reservation
import com.pentatrespassers.neodoollae.view.login.main.invitation.InvitationFriendAdapter
import com.pentatrespassers.neodoollae.view.login.main.invitation.InvitationFriendListAdapter
import splitties.toast.toast

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
            invitationFriendAdapter = InvitationFriendAdapter(this@InvitationChooseFriendActivity, arrayListOf())
            invitationFriendListAdapter = InvitationFriendListAdapter(this@InvitationChooseFriendActivity, arrayListOf())

            invitationFriendRecycler.adapter = invitationFriendAdapter
            invitationFriendListRecycler.adapter = invitationFriendListAdapter

            invitationFriendAdapter.notifyDataSetChanged()
            invitationFriendListAdapter.notifyDataSetChanged()


            invitationOkButton.setOnClickListener {
                //
                toast("친구선택이 완료되었습니다")
                finish()
            }
            invitationDeclineButton.setOnClickListener {
                toast("친구선택이 취되었습니다")
                finish()
            }


        }
    }





}