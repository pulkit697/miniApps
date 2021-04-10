package com.example.networking_java

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_item.view.*

class UsersListAdapter(private val users_list:List<GitHubUser>): RecyclerView.Adapter<UsersListAdapter.UserViewHolder>() {
    class UserViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        fun onBind(user:GitHubUser)
        {
            itemView.tvId.text = user.id.toString();
            itemView.tvLogin.text = user.login;
            itemView.tvScore.text = user.score.toString();
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder =
            UserViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_item,parent,false))

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.onBind(users_list.get(position));
    }

    override fun getItemCount(): Int =  users_list.size
}