package com.pentatrespassers.neodoollae.view.login.main.friend.friendlist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.databinding.CellFriendListBinding
import com.pentatrespassers.neodoollae.dto.User
import com.pentatrespassers.neodoollae.lib.Util.gone
import com.pentatrespassers.neodoollae.lib.Util.show
import com.pentatrespassers.neodoollae.network.RetrofitClient
import com.pentatrespassers.neodoollae.network.body.FavoriteBody
import splitties.activities.start
import splitties.bundle.putExtras

class FriendListAdapter(private val context: Context, private val borderViews: List<View>? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {

    var userList: ArrayList<User> = arrayListOf()
    val layoutInflater: LayoutInflater = LayoutInflater.from(context)
    private lateinit var differentAdapter: FriendListAdapter
    var forFavorite: Boolean = true

    fun init(differentAdapter: FriendListAdapter, forFavorite: Boolean) {
        this.differentAdapter = differentAdapter
        this.forFavorite = forFavorite
    }

    private var filteredUserList: ArrayList<User> = userList

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
                val pop = PopupMenu(itemView.context, itemView)
                pop.inflate(R.menu.friend_list_menu)
                // 즐겨찾기
                pop.menu.getItem(0).apply {
                    if (forFavorite) {
                        title = context.getString(R.string.removeFavorite)
                        setOnMenuItemClickListener {
                            RetrofitClient.setFavorite(FavoriteBody(user.id, false)) { _, _ ->
                                differentAdapter.apply {
                                    userList.add(user)
                                    refresh()
                                }
                                userList.remove(user)
                                refresh()
                            }
                            true
                        }
                    } else {
                        title = context.getString(R.string.addFavorite)
                        setOnMenuItemClickListener {
                            RetrofitClient.setFavorite(FavoriteBody(user.id, true)) { _, _ ->
                                differentAdapter.apply {
                                    userList.add(user)
                                    refresh()
                                }
                                userList.remove(user)
                                refresh()
                            }
                            true
                        }
                    }

                }
                itemView.setOnLongClickListener {
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
        val data = filteredUserList[position]
        (holder as CellFriendListHolder).binding(data)
    }

    override fun getItemCount(): Int {
        return filteredUserList.size
    }

    fun refresh() {
        if (userList.isEmpty()) {
            borderViews?.forEach {
                it.gone()
            }
        } else {
            borderViews?.forEach {
                it.show()
            }
        }

        filter.filter(lastConstraint)
    }

    fun refresh(userList: ArrayList<User>) {
        this.userList = userList
        refresh()
    }

    var lastConstraint = ""

    private val searchFilter = object : Filter() {
        override fun performFiltering(constraint: CharSequence): FilterResults {
            lastConstraint = constraint.toString()
            filteredUserList = if (lastConstraint.isEmpty()) {
                userList
            } else {
                val filteredList = ArrayList<User>()
                for (user in userList) {
                    if (user.nickname.lowercase()
                            .contains(lastConstraint.lowercase())
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

    override fun getFilter(): Filter {
        return searchFilter
    }
}