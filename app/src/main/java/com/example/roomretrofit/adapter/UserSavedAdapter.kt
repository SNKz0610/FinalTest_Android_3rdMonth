package com.example.roomretrofit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.roomretrofit.databinding.ItemFragsaveuserUserBinding
import com.example.roomretrofit.entity.User
import com.example.roomretrofit.fragment.SaveUsersFragment
import com.example.roomretrofit.fragment.SaveUsersFragmentDirections
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.async

class UserSavedAdapter (var lifecycleOwner: LifecycleOwner, var userSaved : SaveUsersFragment) : RecyclerView.Adapter<UserSavedAdapter.ViewHolder>() {
    val arrUsers = mutableListOf(User(0,"", "", ""))

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

    @InternalCoroutinesApi
    override fun onBindViewHolder(holder: UserSavedAdapter.ViewHolder, position: Int) {
        val user : User = arrUsers.get(position)
        holder.bindingUser.textItemuserLogin.text = user.login
        holder.bindingUser.textItemuserType.text = user.type
        holder.bindingUser.textItemuserUrl.text = user.url

        holder.bindingUser.iconEdit.setOnClickListener {
             GlobalScope.async (Dispatchers.IO){
                 val action = SaveUsersFragmentDirections.actionSaveUsersFragmentToEditUserFragment(user)
                 holder.itemView.findNavController().navigate(action)
             }
        }

        holder.bindingUser.iconDelete.setOnClickListener {
            userSaved. showDialog(user)
        }

        holder.bindingUser.textItemuserUrl.setOnClickListener {
            userSaved.connecctURL(user.url)
        }
    }

    override fun getItemCount(): Int {
        return arrUsers.size
    }
}