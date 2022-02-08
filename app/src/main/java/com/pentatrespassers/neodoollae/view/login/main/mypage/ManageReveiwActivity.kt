package com.pentatrespassers.neodoollae.view.login.main.mypage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.databinding.ActivityManageReveiwBinding
import com.pentatrespassers.neodoollae.lib.Util.fragmentTransaction
import splitties.fragments.fragmentTransaction

class ManageReveiwActivity : AppCompatActivity() {

    private val bind by lazy {
        ActivityManageReveiwBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(bind) {
            setContentView(root)

//            fragmentTransaction {
//                add(R.id.friendFrame,friendListFragment)
//                add(R.id.friendFrame,friendRequestFragment)
//                hide(friendRequestFragment)
//            }
//            friendListConstraint.setOnClickListener {
//                fragmentTransaction {
//                    hide(friendRequestFragment)
//                    show(friendListFragment)
//                    friendListText.setTextColor(ContextCompat.getColor(requireContext(), R.color.trespassBlue_900))
//                    friendListUnderlineConstraint.visibility = View.VISIBLE
//                    friendRequestText.setTextColor(ContextCompat.getColor(requireContext(), R.color.trespassGray_900))
//                    friendRequestUnderlineConstraint.visibility = View.GONE
//                }
//            }
//            friendRequestConstraint.setOnClickListener {
//                fragmentTransaction {
//                    hide(friendListFragment)
//                    show(friendRequestFragment)
//                    friendRequestText.setTextColor(ContextCompat.getColor(requireContext(), R.color.trespassBlue_900))
//                    friendRequestUnderlineConstraint.visibility = View.VISIBLE
//                    friendListText.setTextColor(ContextCompat.getColor(requireContext(), R.color.trespassGray_900))
//                    friendListUnderlineConstraint.visibility = View.GONE
//                }
//            }

            backButtonReview.setOnClickListener {
                finish()
            }
        }
    }

}