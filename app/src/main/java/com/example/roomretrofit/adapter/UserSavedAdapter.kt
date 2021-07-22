package com.example.roomretrofit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.roomretrofit.databinding.ItemFragsaveuserUserBinding
import com.example.roomretrofit.entity.User

class UserSavedAdapter (var lifecycleOwner: LifecycleOwner) : RecyclerView.Adapter<UserSavedAdapter.ViewHolder>() {
    val arrUsers = mutableListOf(User("","",""))
    class ViewHolder (val bindingUser: ItemFragsaveuserUserBinding, val lifecycleOwner: LifecycleOwner)
        : RecyclerView.ViewHolder(bindingUser.root) {
        fun bind(user : User){
            bindingUser.lifecycleOwner = lifecycleOwner
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserSavedAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val bindingUser = ItemFragsaveuserUserBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(bindingUser,lifecycleOwner)
    }

    override fun onBindViewHolder(holder: UserSavedAdapter.ViewHolder, position: Int) {
        val user : User = arrUsers.get(position)
        holder.bindingUser.textItemuserLogin.text = user.login
        holder.bindingUser.textItemuserType.text = user.type
        holder.bindingUser.textItemuserUrl.text = user.url
    }

    override fun getItemCount(): Int {
        return arrUsers.size
    }
}