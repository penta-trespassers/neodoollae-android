package com.pentatrespassers.neodoollae.view.login.main.home.addroom.address

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pentatrespassers.neodoollae.databinding.CellSelectAddressBinding
import com.pentatrespassers.neodoollae.databinding.FragmentAddressBinding
import com.pentatrespassers.neodoollae.lib.Util.gone
import com.pentatrespassers.neodoollae.lib.Util.show
import com.pentatrespassers.neodoollae.network.body.searchplace.DocumentBody


class SelectAddressAdapter(private val context: Context, private val fragmentAddressBind: FragmentAddressBinding) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var documentList = arrayListOf<DocumentBody>()
    var document: DocumentBody? = null


    inner class SelectAddressHolder(private val bind: CellSelectAddressBinding) :
        RecyclerView.ViewHolder(bind.root) {
        fun binding(documentBody: DocumentBody) {
            with(bind) {
                addressText.text = documentBody.addressName
                roadAddressText.text = documentBody.roadAddressName
                itemView.setOnClickListener {
                    document = documentBody
                    fragmentAddressBind.selectAddressConstraint.gone()
                    fragmentAddressBind.detailAddressEditText.show()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        return SelectAddressHolder(
            CellSelectAddressBinding.inflate(
                layoutInflater,
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as SelectAddressHolder).binding(documentList[position])
    }

    override fun getItemCount(): Int {
        return documentList.size
    }


}