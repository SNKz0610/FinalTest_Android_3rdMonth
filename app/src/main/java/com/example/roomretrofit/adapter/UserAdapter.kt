package com.example.roomretrofit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.roomretrofit.databinding.ItemFraggetuserUserBinding
import com.example.roomretrofit.entity.User

class UserAdapter (val lifecycleOwner: LifecycleOwner) : RecyclerView.Adapter<UserAdapter.Viewholder>() {
    private lateinit var fragment: Fragment
    var arrUsers : List<User> = ArrayList<User>()

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