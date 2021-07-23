package com.example.roomretrofit.fragment

import android.app.Application
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomretrofit.R
import com.example.roomretrofit.adapter.UserSavedAdapter
import com.example.roomretrofit.database.UserRepository
import com.example.roomretrofit.databinding.FragmentSaveUsersBinding
import com.example.roomretrofit.entity.User
import kotlinx.coroutines.*


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
        bindingUsers = DataBindingUtil.inflate(inflater, R.layout.fragment_save_users, container, false)
        val view: View = bindingUsers.root
        adapter = UserSavedAdapter(this, this)
        bindingUsers.revFragsaveusers.layoutManager = LinearLayoutManager(context)


        createListUsersSaved()
        backToBeforeFragment()

        return view
    }

    private fun backToBeforeFragment() {
        bindingUsers.buttonFragsaveuserBack.setOnClickListener {
            findNavController().navigate(R.id.action_saveUsersFragment_to_getUserFragment)
        }
    }

    @InternalCoroutinesApi
    private fun createListUsersSaved() {
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

    @InternalCoroutinesApi
    fun showDialog (user : User) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") {_, _ ->
            GlobalScope.launch (Dispatchers.IO) {
                userRepository.deleteUser(user)
            }
            Toast.makeText(requireContext(), "Lmao unfollow roi` day", Toast.LENGTH_LONG).show()
        }
        builder.setNegativeButton("No") {_, _ -> }
        builder.setTitle("Confirm unfollow")
        builder.setMessage("Chac chua?")
        builder.create().show()
    }

    fun connecctURL (url : String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }

}