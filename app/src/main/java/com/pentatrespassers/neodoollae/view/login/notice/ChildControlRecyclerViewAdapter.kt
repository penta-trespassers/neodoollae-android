package com.pentatrespassers.neodoollae.view.login.notice

import android.annotation.SuppressLint
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.util.set
import androidx.recyclerview.widget.RecyclerView
import com.pentatrespassers.neodoollae.R

class ChildControlRecyclerViewAdapter(
    val recyclerView: RecyclerView
) :
    RecyclerView.Adapter<ChildControlRecyclerViewAdapter.ViewHolder>() {

    private var dataList = ArrayList<Int>()
    private var sparseArray = SparseArray<Boolean>()
    private var itemView = HashMap<Int, ConstraintLayout>()
    private var imageView = HashMap<Int, ImageView>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.cell_notice, parent, false),
            recyclerView, dataList
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(sparseArray, itemView, imageView)
    }

    override fun getItemCount(): Int {
        return if (dataList.size > 0) dataList.size else 0

    }

    fun addItem(dataList: ArrayList<Int>) {
        this.dataList = dataList
    }

    class ViewHolder(
        itemView: View,
        val recyclerView: RecyclerView,
        val dataList: ArrayList<Int>
    ) :
        RecyclerView.ViewHolder(itemView) {

        private var parentTextView: TextView = itemView.findViewById(R.id.parentTextView)
        private var parentView: ConstraintLayout = itemView.findViewById(R.id.itemParentView)
        private var childViewWrap: ConstraintLayout = itemView.findViewById(R.id.childViewWrap)
        private var item_arrow: ImageView = itemView.findViewById(R.id.item_arrow)

        fun bind(
            sparseArray: SparseArray<Boolean>,
            itemView: HashMap<Int, ConstraintLayout>,
            imageView: HashMap<Int, ImageView>
        ) {
            if (sparseArray[adapterPosition] == null) {
                sparseArray.put(adapterPosition, false)
            }

            parentTextView.text = dataList[adapterPosition].toString()
            itemView[adapterPosition] = childViewWrap
            imageView[adapterPosition] = item_arrow

            if (!sparseArray[adapterPosition]) {
                collapseItem(
                    itemView[adapterPosition]!!,
                    imageView[adapterPosition]!!
                )
            } else {
                expandItem(
                    itemView[adapterPosition]!!,
                    imageView[adapterPosition]!!
                )
            }

            parentView.setOnClickListener {
                //cardView가 펼쳐질 때 부드러움 추가
                TransitionManager.beginDelayedTransition(childViewWrap, AutoTransition())

                when (childViewWrap.visibility) {
                    View.VISIBLE -> {
                        sparseArray[adapterPosition] = false
                        childViewWrap.visibility = View.GONE
                        collapseItem(childViewWrap, item_arrow)
                    }

                    View.GONE -> {
                        sparseArray[adapterPosition] = true
                        //펼쳐질 때 adapterPosition의 아이템이 top으로 이동하도록 설정
                        recyclerView.smoothScrollToPosition(adapterPosition)
                        childViewWrap.visibility = View.VISIBLE
                        expandItem(childViewWrap, item_arrow)

                        //클릭 시 펼쳐져있는 item collapse
                        //제 코드로는 scrollToTop과 같이 사용하면 문제가 있습니다.
//                        for (i in 0..sparseArray.size()) {
//                            if (sparseArray[i] == true) {
//                                itemView[i]!!.visibility = View.GONE
//                                imageView[i]!!.apply {
//                                    setImageDrawable(
//                                        resources.getDrawable(
//                                            R.drawable.ic_arrow_down,
//                                            null
//                                        )
//                                    )
//                                }
//                                sparseArray[i] = false
//                            }
//                        }
                    }
                }
            }
        }

        @SuppressLint("UseCompatLoadingForDrawables")
        fun expandItem(view: View, imageView: ImageView) {
            view.visibility = View.VISIBLE
            imageView.apply {
                setImageDrawable(
                    this.context.resources.getDrawable(
                        R.drawable.ic_baseline_keyboard_arrow_up_24,
                        null
                    )
                )
            }
        }

        @SuppressLint("UseCompatLoadingForDrawables")
        fun collapseItem(view: View, imageView: ImageView) {
            view.visibility = View.GONE
            imageView.apply {
                setImageDrawable(
                    this.context.resources.getDrawable(
                        R.drawable.ic_baseline_keyboard_arrow_down_24,
                        null
                    )
                )
            }
        }
    }

}