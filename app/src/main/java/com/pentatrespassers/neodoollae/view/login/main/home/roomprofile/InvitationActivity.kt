package com.pentatrespassers.neodoollae.view.login.main.home.roomprofile

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.pentatrespassers.neodoollae.databinding.ActivityInvitationEditBinding
import com.pentatrespassers.neodoollae.dto.Reservation
import com.pentatrespassers.neodoollae.view.login.main.reservation.ToggleAnimation
import splitties.toast.toast

class InvitationActivity : AppCompatActivity(){
    var invitation : Reservation = Reservation()

    var isDateExpanded : Boolean = false
    var isTimeExpanded : Boolean = false

    var num : Int = 0

    private val bind by lazy {
        ActivityInvitationEditBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(bind) {
            setContentView(root)

            var intent : Intent = getIntent()
            invitation = intent.getParcelableExtra("invitation")!!


            invitationRoomNameText.text = invitation.roomName
            invitationVisitorNameText.text = invitation.nickname



            settingDateAndTimeConstraintLayout.setOnClickListener {
                //toggleLayout(!isDateExpanded, it, layoutExpandDate)
                isDateExpanded = true
            }
            invitationCalendarView.setOnClickListener {
              // toggleLayout(!isTimeExpanded, it, layoutExpandTime)
               // isTimeExpanded = true
            }

            invitationSetDateButton.setOnClickListener{
               // toggleLayout(!isDateExpanded, it, layoutExpandDate)
                //toggleLayout(!isTimeExpanded, it, layoutExpandTime)
                isDateExpanded = false
                isTimeExpanded = false

            }


            invitationAddButton.setOnClickListener {
                toast("초대가 완료되었습니다.")
                //   var intent = Intent(this@invitationEditActivity,invitationFragment)
                // startActivity(intent)
                finish()

            }
            invitationSetDateButton.setOnClickListener {


            }



        }

    }

    private fun toggleLayout(isExpanded: Boolean, view: View, layoutExpand: View) {

        ToggleAnimation.toggleArrow(view, isExpanded)
        if (isExpanded) {
            ToggleAnimation.expand(layoutExpand)
        }else{
            ToggleAnimation.collapse(layoutExpand)
        }
    }
}