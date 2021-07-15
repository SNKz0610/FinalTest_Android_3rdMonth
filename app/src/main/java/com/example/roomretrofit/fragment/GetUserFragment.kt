package com.example.roomretrofit.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomretrofit.R
import com.example.roomretrofit.adapter.UserAdapter
import com.example.roomretrofit.databinding.FragmentGetUserBinding
import com.example.roomretrofit.entity.User
import com.example.roomretrofit.viewmodel.UserViewModel
import com.example.roomretrofit.viewmodel.UserViewModelCallback


class GetUserFragment : Fragment(), UserViewModelCallback {
    private lateinit var binding : FragmentGetUserBinding
    private var userVM : UserViewModel? = null
    val arrayUser : ArrayList<User> = ArrayList()
    val adapter: UserAdapter = UserAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_get_user, container, false)
        val view : View =  binding.root

        adapter.arrUsers = arrayUser;
        binding.revFraggetuserUser.adapter = adapter
        binding.revFraggetuserUser.layoutManager = LinearLayoutManager(context)
        adapter.notifyDataSetChanged()
        userVM = UserViewModel()
        userVM!!.callBack = this
        userVM!!.getAllUser()
        return view
    }

    override fun getListUser(item: ArrayList<User>) {
        adapter.arrUsers = item;
        adapter.notifyDataSetChanged();
    }


}