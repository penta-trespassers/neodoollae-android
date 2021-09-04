package com.pentatrespassers.neodoollae.view.login.main.around

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pentatrespassers.neodoollae.databinding.CellMaplistitemBinding

class mapListRecylerViewAdapter
    (private val context: Context, private val dataList: ArrayList<Any>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class SkeletonHolder(private val bind: CellMaplistitemBinding) :
        RecyclerView.ViewHolder(bind.root) {
        fun binding(data: Any) {
            // cell 내용 넣기
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        return SkeletonHolder(
            CellMaplistitemBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = dataList[position]
        (holder as SkeletonHolder).binding(data)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
    }