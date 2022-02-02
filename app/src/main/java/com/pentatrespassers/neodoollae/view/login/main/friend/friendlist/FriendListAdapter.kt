package com.pentatrespassers.neodoollae.view.login.main.friend.friendlist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.databinding.CellFriendListBinding
import com.pentatrespassers.neodoollae.dto.User
import splitties.activities.start
import splitties.bundle.putExtras
import splitties.toast.toast

class FriendListAdapter(private var context: Context, private var userList: ArrayList<User>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {

    val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    private var users: ArrayList<User> = userList

    inner class CellFriendListHolder(private val bind: CellFriendListBinding) :
        RecyclerView.ViewHolder(bind.root) {
        fun binding(user: User) {
            with(bind) {
                nicknameTextFriendList.text = user.nickname
                itemView.setOnClickListener {
                    context.start<FriendProfileActivity> {
                        putExtras(FriendProfileActivity.Extras) {
                            this.user = user
                        }
                    }
                }
                itemView.setOnLongClickListener {
                    toast("this is long click")

                    val pop = PopupMenu(itemView.context, it)
                    pop.inflate(R.menu.friend_list_menu)

                    pop.setOnMenuItemClickListener { item ->

                        when (item.itemId) {
                            R.id.bookmarkItem -> {
                                toast("즐겨찾기에 추가되었습니다.")
                            }

                            R.id.deleteItem -> {}
                        }
                        true
                    }
                    pop.show()
                    true
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return CellFriendListHolder(CellFriendListBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = users[position]
        (holder as CellFriendListHolder).binding(data)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    fun refresh(userList: ArrayList<User>) {
        this.userList = userList
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence): FilterResults {
                val charString = constraint.toString()
                users = if (charString.isEmpty()) {
                    userList
                } else {
                    val filteredList = ArrayList<User>()
                    for (user in userList) {
                        if (user.nickname.lowercase()
                                .contains(charString.lowercase())
                        ) {
                            filteredList.add(user);
                        }
                    }
                    filteredList
                }
                return FilterResults()
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                notifyDataSetChanged()
            }
        }
    }
}