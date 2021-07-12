package com.example.roomretrofit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomretrofit.databinding.ItemFraggetuserUserBinding
import com.example.roomretrofit.entity.User

class UserAdapter (val arrUsers : List<User>) : RecyclerView.Adapter<UserAdapter.Viewholder>() {
    private var statusFarvorite = 1;
    private lateinit var fragment: Fragment

    class Viewholder(val binding: ItemFraggetuserUserBinding) :
        RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.Viewholder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemFraggetuserUserBinding.inflate(layoutInflater, parent, false)
        fragment = FragmentManager.findFragment(parent)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: UserAdapter.Viewholder, position: Int) {
        val user: User = arrUsers[position]
        if (user != null) {
            holder.binding.textItemuserLogin.text = "Login: " + user.login
            holder.binding.textItemuserType.text = "Type: " + user.type
            holder.binding.textItemuserUrl.text = "URL: " + user.url
        }
    }


    override fun getItemCount(): Int {
        if (arrUsers != null) {
            return arrUsers.size
        }
        return 0
    }
}