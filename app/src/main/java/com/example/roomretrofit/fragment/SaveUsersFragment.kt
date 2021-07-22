package com.example.roomretrofit.fragment

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomretrofit.R
import com.example.roomretrofit.adapter.UserSavedAdapter
import com.example.roomretrofit.database.UserRepository
import com.example.roomretrofit.databinding.FragmentSaveUsersBinding
import com.example.roomretrofit.entity.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.async


class SaveUsersFragment : Fragment() {
    private lateinit var bindingUsers: FragmentSaveUsersBinding
    private lateinit var adapter: UserSavedAdapter
    private lateinit var userRepository: UserRepository
    private lateinit var application: Application

    @InternalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingUsers =
            DataBindingUtil.inflate(inflater, R.layout.fragment_save_users, container, false)
        val view: View = bindingUsers.root
        adapter = UserSavedAdapter(this)
        bindingUsers.revFragsaveusers.layoutManager = LinearLayoutManager(context)


        createListUsersSaved()
        backToBeforeFragment()

        return view
    }

    private fun backToBeforeFragment() {
        bindingUsers.buttonFragsaveuserBack.setOnClickListener {
            val action = SaveUsersFragmentDirections.actionSaveUsersFragmentToGetUserFragment()
            findNavController().navigate(action)
        }
    }

    @InternalCoroutinesApi
    private fun createListUsersSaved() {
        bindingUsers.buttonFragsaveuserShow.setOnClickListener {
            bindingUsers.revFragsaveusers.adapter = adapter
            userRepository = UserRepository(this.requireActivity().application)
            userRepository.getAllUsers().observe(viewLifecycleOwner) {
                if (it != null) {
                    for (user in it) {
                        adapter.arrUsers.add(user)
                    }

                    adapter.arrUsers.removeAt(0)
                    adapter.notifyDataSetChanged()
                }

            }

        }
    }
}