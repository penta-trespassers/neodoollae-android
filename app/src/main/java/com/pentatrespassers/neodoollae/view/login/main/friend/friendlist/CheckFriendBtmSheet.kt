package com.pentatrespassers.neodoollae.view.login.main.friend.friendlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.databinding.BtmSheetAddFriendBinding
import com.pentatrespassers.neodoollae.databinding.BtmSheetCheckFriendBinding

class CheckFriendBtmSheet : BottomSheetDialogFragment() {

    private lateinit var bind: BtmSheetCheckFriendBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Binding
        bind = BtmSheetCheckFriendBinding.inflate(inflater, container, false)
        with(bind) {

            return root
        }
    }
}