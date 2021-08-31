package com.pentatrespassers.neodoollae.debug

//import android.content.Context
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//
//class AdapterSkeleton(private val context: Context, private val dataList: ArrayList<Any>) :
//    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
//
//    val layoutInflater: LayoutInflater = LayoutInflater.from(context)
//
//    inner class SkeletonHolder(private val bind: SkeletonBinding) :
//        RecyclerView.ViewHolder(bind.root) {
//        fun binding(data: Any) {
//            // Do anything
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//
//        return SkeletonHolder(
//            SkeletonBinding.inflate(layoutInflater, parent, false)
//        )
//    }
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        val data = dataList[position]
//        (holder as SkeletonHolder).binding(data)
//    }
//
//    override fun getItemCount(): Int {
//        return dataList.size
//    }
//}