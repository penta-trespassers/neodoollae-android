package com.pentatrespassers.neodoollae.view.login.main.home.addroom

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat.setImageTintList
import androidx.fragment.app.Fragment
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.databinding.FragmentRoomOperationBinding
import com.pentatrespassers.neodoollae.dto.Room
import com.pentatrespassers.neodoollae.lib.Util.gone
import com.pentatrespassers.neodoollae.lib.Util.show

class RoomOperationFragment private constructor() : Fragment() {

    private lateinit var bind: FragmentRoomOperationBinding

    var operation : Int = Room.STATUS_OPEN

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentRoomOperationBinding.inflate(inflater, container, false)
        with(bind) {
            setOpen()

            openConstraintRoomOperation.setOnClickListener {
                setOpen()
            }
            restrictConstraintRoomOperation.setOnClickListener {
                setRestrict()
            }
            closeConstraintRoomOperation.setOnClickListener {
                setClose()
            }

            return root
        }
    }

    companion object {
        fun newInstance() = RoomOperationFragment()
    }

    private fun setOpen() {
        with(bind) {
            operation = Room.STATUS_OPEN
            selectDescriptionRoomOperation.text = getString(R.string.open_description)
            setImageTintList(openImageRoomOperation,
                ColorStateList.valueOf(ContextCompat.getColor(requireContext(),
                    R.color.trespassBlue_600)))
            setImageTintList(restrictImageRoomOperation,
                ColorStateList.valueOf(ContextCompat.getColor(requireContext(),
                    R.color.trespassGray_300)))
            setImageTintList(closeImageRoomOperation,
                ColorStateList.valueOf(ContextCompat.getColor(requireContext(),
                    R.color.trespassGray_300)))
            restrictCalendarConstraintRoomOperation.gone()
        }
    }

    private fun setRestrict() {
        with(bind) {
            operation = Room.STATUS_RESTRICTED
            selectDescriptionRoomOperation.text = getString(R.string.restrict_description)
            setImageTintList(openImageRoomOperation,
                ColorStateList.valueOf(ContextCompat.getColor(requireContext(),
                    R.color.trespassGray_300)))
            setImageTintList(restrictImageRoomOperation,
                ColorStateList.valueOf(ContextCompat.getColor(requireContext(),
                    R.color.trespassBlue_600)))
            setImageTintList(closeImageRoomOperation,
                ColorStateList.valueOf(ContextCompat.getColor(requireContext(),
                    R.color.trespassGray_300)))
            restrictCalendarConstraintRoomOperation.show()
        }
    }

    private fun setClose() {
        with(bind) {
            operation = Room.STATUS_CLOSED
            selectDescriptionRoomOperation.text = getString(R.string.close_description)
            setImageTintList(openImageRoomOperation,
                ColorStateList.valueOf(ContextCompat.getColor(requireContext(),
                    R.color.trespassGray_300)))
            setImageTintList(restrictImageRoomOperation,
                ColorStateList.valueOf(ContextCompat.getColor(requireContext(),
                    R.color.trespassGray_300)))
            setImageTintList(closeImageRoomOperation,
                ColorStateList.valueOf(ContextCompat.getColor(requireContext(),
                    R.color.trespassBlue_600)))
            restrictCalendarConstraintRoomOperation.gone()
        }
    }
}