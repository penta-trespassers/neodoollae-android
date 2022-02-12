package com.pentatrespassers.neodoollae.view.login.main.home.roomprofile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pentatrespassers.neodoollae.databinding.ActivityInvitationChooseFriendBinding
import com.pentatrespassers.neodoollae.network.RetrofitClient
import com.pentatrespassers.neodoollae.view.login.main.home.roomprofile.invitation.InvitationFriendAdapter
import com.pentatrespassers.neodoollae.view.login.main.home.roomprofile.invitation.InvitationFriendListAdapter
import com.pentatrespassers.neodoollae.view.login.main.home.roomprofile.invitation.InviteFriend
import splitties.toast.toast

class InvitationChooseFriendActivity : AppCompatActivity(){

    private val bind by lazy {
        ActivityInvitationChooseFriendBinding.inflate(layoutInflater)
    }

    private val invitationFriendAdapter by lazy {
        InvitationFriendAdapter(this)
    }

    private val invitationFriendListAdapter by lazy {
        InvitationFriendListAdapter(this)
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(bind) {
            setContentView(root)

            invitationFriendListAdapter.init(invitationFriendAdapter)
            invitationFriendAdapter.init(invitationFriendListAdapter)
            invitationFriendRecycler.adapter = invitationFriendAdapter
            invitationFriendListRecycler.adapter = invitationFriendListAdapter

        RetrofitClient.getAllFriends { _, response ->
            val users = response.body()!!
            users[0].addAll(users[1])
            var list : ArrayList<InviteFriend> = arrayListOf()
            for (item in users[0]){
                list.add(InviteFriend(item,false))
            }
            invitationFriendListAdapter.refresh(list)
        }

           // invitationFriendAdapter.notifyDataSetChanged()
            //invitationFriendListAdapter.notifyDataSetChanged()


            invitationOkButton.setOnClickListener {
                //
                toast("친구선택이 완료되었습니다")
                finish()
            }
            invitationDeclineButton.setOnClickListener {
                toast("친구선택이 취되었습니다")
                finish()
            }

            backButton.setOnClickListener {
                finish()
            }


        }
    }







}