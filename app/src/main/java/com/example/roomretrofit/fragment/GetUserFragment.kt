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


class GetUserFragment : Fragment() {
    private lateinit var binding : FragmentGetUserBinding
    private val userVM : UserViewModel = UserViewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_get_user, container, false)
        val view : View =  binding.root


        binding.revFraggetuserUser.adapter = UserAdapter(userVM.getAllUser())
        binding.revFraggetuserUser.layoutManager = LinearLayoutManager(context)
        return view
    }


}