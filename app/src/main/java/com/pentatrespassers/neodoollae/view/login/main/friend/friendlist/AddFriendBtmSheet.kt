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

class AddFriendBtmSheet : BottomSheetDialogFragment() {

    private lateinit var bind: BtmSheetAddFriendBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Binding
        bind = BtmSheetAddFriendBinding.inflate(inflater, container, false)
        with (bind) {

            return root
        }
    }
}