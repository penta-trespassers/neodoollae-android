package com.pentatrespassers.neodoollae.view.login.main.mypage

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.databinding.ActivityVisitHistoryBinding
import com.pentatrespassers.neodoollae.view.login.main.mypage.visithistory.MyVisitFragment
import com.pentatrespassers.neodoollae.view.login.main.mypage.visithistory.RoomVisitFragment
import splitties.fragments.fragmentTransaction

class VisitHistoryActivity : AppCompatActivity() {

    private val bind by lazy {
        ActivityVisitHistoryBinding.inflate(layoutInflater)
    }

    
    private val myVisitFragment = MyVisitFragment.newInstance()
    private val roomVisitFragment = RoomVisitFragment.newInstance()
    
    @SuppressLint("UnsafeOptInUsageError")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(bind) {
            setContentView(root)

            fragmentTransaction {
                add(R.id.visitHistoryFrame, myVisitFragment)
                add(R.id.visitHistoryFrame, roomVisitFragment)
                hide(roomVisitFragment)
            }

            myVisitConstraint.setOnClickListener {
                fragmentTransaction {
                    hide(roomVisitFragment)
                    show(myVisitFragment)
                    myVisitText.setTextColor(ContextCompat.getColor(root.context, R.color.trespassBlue_900))
                    myVisitUnderlineConstraint.visibility = View.VISIBLE
                    roomVisitText.setTextColor(ContextCompat.getColor(root.context, R.color.trespassGray_900))
                    roomVisitUnderlineConstraint.visibility = View.GONE
                }
            }
            
            roomVisitConstraint.setOnClickListener {
                fragmentTransaction {
                    hide(myVisitFragment)
                    show(roomVisitFragment)
                    roomVisitText.setTextColor(ContextCompat.getColor(root.context, R.color.trespassBlue_900))
                    roomVisitUnderlineConstraint.visibility = View.VISIBLE
                    myVisitText.setTextColor(ContextCompat.getColor(root.context, R.color.trespassGray_900))
                    myVisitUnderlineConstraint.visibility = View.GONE
                }
            }

            backButtonVisit.setOnClickListener {
                finish()
            }
        }
    }
}