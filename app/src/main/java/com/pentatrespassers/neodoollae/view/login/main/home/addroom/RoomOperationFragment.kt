package com.pentatrespassers.neodoollae.view.login.main.home.addroom

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.core.widget.ImageViewCompat.setImageTintList
import androidx.fragment.app.Fragment
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.databinding.FragmentRoomOperationBinding
import com.pentatrespassers.neodoollae.lib.Util.gone
import com.pentatrespassers.neodoollae.lib.Util.show

class RoomOperationFragment private constructor() : Fragment() {

    private lateinit var bind: FragmentRoomOperationBinding

    var operation : Int = 0

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
            operation = 0
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
            operation = 1
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
            operation = 2
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