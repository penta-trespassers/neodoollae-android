package com.pentatrespassers.neodoollae.view.login.main.home.roomprofile

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.pentatrespassers.neodoollae.databinding.ActivityInvitationEditBinding
import com.pentatrespassers.neodoollae.dto.Reservation
import com.pentatrespassers.neodoollae.lib.Util
import com.pentatrespassers.neodoollae.lib.Util.hide
import com.pentatrespassers.neodoollae.view.login.main.reservation.ToggleAnimation
import splitties.activities.start
import splitties.bundle.BundleSpec
import splitties.bundle.bundle
import splitties.bundle.withExtras
import splitties.toast.toast
import java.sql.Timestamp
import java.util.*

class InvitationEditActivity : AppCompatActivity(){

    object Extras : BundleSpec() {
        var invitation: Reservation by bundle()
    }

    private val invitation by lazy {
        withExtras(Extras) {
            invitation
        }
    }

    var isDateExpanded : Boolean = false
    var isTimeExpanded : Boolean = false

    var isStartDateSet = false

    val calendar = Calendar.getInstance()
    val formatter = Util.getDateFormatter("yy MM dd EEEE")



    fun timeOnClick(button: View){
        var time : String = (button as Button).text as String
        var timestamp : Timestamp? = null


        time.split(":").let{
            calendar.set(Calendar.HOUR_OF_DAY, it[0].toInt())
            calendar.set(Calendar.MINUTE, it[1].toInt())
            timestamp = Timestamp(calendar.timeInMillis)

        }

        with(bind){
            if(!isStartDateSet) {
                visitStartTimeText.text = time
                invitation.checkIn = timestamp

                isStartDateSet = true
            }
            else{
                visitEndTimeText.text = time
                invitation.checkOut = timestamp
            }
        }

    }

    private val bind by lazy {
        ActivityInvitationEditBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(bind) {
            setContentView(root)

            invitationRoomNameText.text = invitation.roomName
            invitationVisitorNameText.text = invitation.nickname
            expandDateConstraintLayout.hide()
            expandTimeConstraintLayout.hide()

           // toHostEditText.setText(invitation.requestMessage)

            invitationCalendarView.setClickable(true)


            invitationVisitorImageView.setOnClickListener {
                start<InvitationChooseFriendActivity> ()
            }

            vistStartDatetext.setOnClickListener {
                isStartDateSet = false
            }
            visitEndDateText.setOnClickListener {
                isStartDateSet = true
            }
            settingDateAndTimeConstraintLayout.setOnClickListener {
               isDateExpanded = toggleLayout(!isDateExpanded, it, expandDateConstraintLayout)

                isTimeExpanded = false
            }
            invitationCalendarView.minDate = System.currentTimeMillis() - 1000
            invitationCalendarView.setOnDateChangeListener{ view, year, month, dayOfMonth ->
                calendar.set(year, month, dayOfMonth)

                if(!isTimeExpanded)
                    toggleLayout(!isTimeExpanded, view, expandTimeConstraintLayout)
                isTimeExpanded = true

                if (isStartDateSet == false) {
                    vistStartDatetext.text = formatter.format(calendar.time)
                } else {
                    visitEndDateText.text = formatter.format(calendar.time)
                }
            }

            invitationSetDateButton.setOnClickListener{
                toggleLayout(!isDateExpanded, it, expandDateConstraintLayout)
                toggleLayout(!isTimeExpanded, it, expandTimeConstraintLayout)
                isDateExpanded = false
                isTimeExpanded = false

            }


            invitationAddButton.setOnClickListener {
                toast("초대가 완료되었습니다.")
                //  invitationn.checkIn =
                // invitationn.checkOut =
               // invitationAdapter.editinvitations(invitation)
                print(invitation.member)
                finish()

            }

            backButton.setOnClickListener {
                finish()
            }


        }

    }

    private fun toggleLayout(isExpanded: Boolean, view: View, layoutExpand: View) : Boolean {

        ToggleAnimation.toggleArrow(view, isExpanded)
        if (isExpanded) {
            ToggleAnimation.expand(layoutExpand)
        }else{
            ToggleAnimation.collapse(layoutExpand)
        }
        return isExpanded
    }
}