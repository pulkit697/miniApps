package com.example.mvvmgithubapi.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvmgithubapi.R
import com.example.mvvmgithubapi.data.model.GithubUser
import kotlinx.android.synthetic.main.layout_user_item.view.*

class UsersListAdapter(val usersList:List<GithubUser>): RecyclerView.Adapter<UsersListAdapter.UserViewHolder>() {
    class UserViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        fun onBind(user:GithubUser) = with(itemView){
            tvUserName.text = user.login
            Glide.with(this).load(user.avatarUrl).into(this.ivUserImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
        UserViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_user_item,parent,false))

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.onBind(usersList[position])
    }

    override fun getItemCount(): Int = usersList.size
}