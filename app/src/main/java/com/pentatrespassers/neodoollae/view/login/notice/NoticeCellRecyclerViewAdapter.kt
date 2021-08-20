package com.pentatrespassers.neodoollae.view.login.notice

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

class NoticeCellRecyclerViewAdapter(
    private val recyclerView: RecyclerView
) :
    RecyclerView.Adapter<NoticeCellRecyclerViewAdapter.ViewHolder>() {

    private var dataList = ArrayList<String>()
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
        return dataList.size

    }

    fun addItem(dataList: ArrayList<String>) {
        this.dataList = dataList
    }

    class ViewHolder(
        itemView: View,
        private val recyclerView: RecyclerView,
        private val dataList: ArrayList<String>
    ) :
        RecyclerView.ViewHolder(itemView) {

        private var reserveInfoText: TextView = itemView.findViewById(R.id.reserveInfoTextNotice)

        /*
        private var friendNameText: TextView = itemView.findViewById(R.id.friendNameTextNotice)
        private var reserveDateText: TextView = itemView.findViewById(R.id.reserveDateNameNotice)
        private var reserveRoomText: TextView = itemView.findViewById(R.id.reserveRoomTextNotice)
        private var visitingPeopleNumberText: TextView = itemView.findViewById(R.id.visitingPeopleTextNotice)
        private var checkInTimeText: TextView = itemView.findViewById(R.id.checkInTimeTextNotice)
        private var checkOutTimeText: TextView = itemView.findViewById(R.id.checkOutTimeTextNotice)
        private var messageToHost: TextView = itemView.findViewById(R.id.messageToHostTextNotice)
        private var messageToGuest: EditText = itemView.findViewById(R.id.messageToGuestEditTextNotice)
        private var acceptButton: Button = itemView.findViewById(R.id.acceptButtonNotice)
        private var declineButton: Button = itemView.findViewById(R.id.declineButtonNotice)
        */
        private var cellLayout: ConstraintLayout = itemView.findViewById(R.id.cellLayoutNotice)
        private var expandedCellLayout: ConstraintLayout =
            itemView.findViewById(R.id.expandedCellLayoutNotice)
        private var arrowImage: ImageView = itemView.findViewById(R.id.arrowImageNotice)

        fun bind(
            sparseArray: SparseArray<Boolean>,
            itemView: HashMap<Int, ConstraintLayout>,
            imageView: HashMap<Int, ImageView>
        ) {
            if (sparseArray[adapterPosition] == null) {
                sparseArray.put(adapterPosition, false)
            }

            reserveInfoText.text = dataList[adapterPosition]
            itemView[adapterPosition] = expandedCellLayout
            imageView[adapterPosition] = arrowImage

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

            cellLayout.setOnClickListener {
                //cardView가 펼쳐질 때 부드럽게
                TransitionManager.beginDelayedTransition(expandedCellLayout, AutoTransition())

                when (expandedCellLayout.visibility) {
                    View.VISIBLE -> {
                        sparseArray[adapterPosition] = false
                        expandedCellLayout.visibility = View.GONE
                        collapseItem(expandedCellLayout, arrowImage)
                    }

                    View.GONE -> {
                        sparseArray[adapterPosition] = true
                        //펼쳐질 때 adapterPosition의 아이템이 top으로 이동하도록 설정
                        recyclerView.scrollToPosition(adapterPosition)
                        expandedCellLayout.visibility = View.VISIBLE
                        expandItem(expandedCellLayout, arrowImage)
                    }
                }
            }
        }

        private fun expandItem(view: View, imageView: ImageView) {
            view.visibility = View.VISIBLE
            imageView.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24)

        }

        private fun collapseItem(view: View, imageView: ImageView) {
            view.visibility = View.GONE
            imageView.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24)

        }
    }


}