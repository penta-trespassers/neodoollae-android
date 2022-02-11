package com.pentatrespassers.neodoollae.view.login.main.invite

import android.content.Intent
import android.content.Intent.getIntent
import android.os.Bundle
import android.view.View
import android.widget.CalendarView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.pentatrespassers.neodoollae.databinding.ActivityInvitationEditBinding
import com.pentatrespassers.neodoollae.databinding.ActivityReservationEditBinding
import com.pentatrespassers.neodoollae.dto.Reservation
import com.pentatrespassers.neodoollae.view.login.main.reservation.ReservationAdapter
import com.pentatrespassers.neodoollae.view.login.main.reservation.ReservationEditActivity
import com.pentatrespassers.neodoollae.view.login.main.reservation.ToggleAnimation
import splitties.toast.toast
import java.text.SimpleDateFormat
import java.util.*

class InvitationEditActivity : AppCompatActivity(){
    var invitation : Reservation = Reservation()

    var isDateExpanded : Boolean = false
    var isTimeExpanded : Boolean = false

    var isStartDateSet = false





    private val bind by lazy {
        ActivityInvitationEditBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(bind) {
            setContentView(root)

            var intent : Intent = getIntent()
            invitation = intent.getParcelableExtra("Reservation")!!


            invitationRoomNameText.text = invitation.roomName
            invitationVisitorNameText.text = invitation.nickname
            vistStartDatetext.text = invitation.checkIn.toString()
            visitStartTimeText.text = invitation.checkIn.toString()
            visitEndDateText.text = invitation.checkOut.toString()
            visitEndTimeText.text = invitation.checkOut.toString()


           // toHostEditText.setText(invitation.requestMessage)

            invitationCalendarView.setClickable(true)


            invitationVisitorImageView.setOnClickListener {
                var intent = Intent(this@InvitationEditActivity, InvitationChooseFriendActivity::class.java)
                startActivity(intent)
            }
            settingDateAndTimeConstraintLayout.setOnClickListener {
                isDateExpanded = toggleLayout(!isDateExpanded, it, layoutExpandDate)

                isTimeExpanded = true
            }

            invitationCalendarView.setOnDateChangeListener(CalendarView.OnDateChangeListener { view, year, month, dayOfMonth ->
                val month = month + 1
                val calendar = Calendar.getInstance()
                calendar.set(year, month, dayOfMonth)
                val date = calendar.time
                val simpledateformat = SimpleDateFormat("EEEE", Locale.getDefault())
                val dayName: String = simpledateformat.format(date)

                toggleLayout(!isTimeExpanded, view, layoutExpandTime)
                isTimeExpanded = true

                if (isStartDateSet == false) {
                    vistStartDatetext.text = "$year.$month.$dayOfMonth.$dayName"
                } else {
                    visitEndDateText.text = "$year.$month.$dayOfMonth.$dayName"

                }
            })

            SetTimeEndbutton.setOnClickListener{
                toggleLayout(!isDateExpanded, it, layoutExpandDate)
                toggleLayout(!isTimeExpanded, it, layoutExpandTime)
                isDateExpanded = false
                isTimeExpanded = false

            }


            invitationAddButton.setOnClickListener {
                toast("예약이 완료되었습니다.")
                //  invitationn.checkIn =
                // invitationn.checkOut =
               // invitationAdapter.editinvitations(invitation)
                print(invitation.member)
                finish()

            }


        }

    }

    private fun toggleLayout(isExpanded: Boolean, view: View, layoutExpand: LinearLayout) : Boolean {

        ToggleAnimation.toggleArrow(view, isExpanded)
        if (isExpanded) {
            ToggleAnimation.expand(layoutExpand)
        }else{
            ToggleAnimation.collapse(layoutExpand)
        }
        return isExpanded
    }
}