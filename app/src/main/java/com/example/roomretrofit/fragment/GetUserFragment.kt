package com.example.roomretrofit.fragment

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomretrofit.R
import com.example.roomretrofit.adapter.UserAdapter
import com.example.roomretrofit.database.UserRepository
import com.example.roomretrofit.databinding.FragmentGetUserBinding
import com.example.roomretrofit.entity.User
import com.example.roomretrofit.service.UserService
import com.example.roomretrofit.viewmodel.UserViewModel
import com.example.roomretrofit.viewmodel.UserViewModelCallback
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.async
import retrofit2.Call


class GetUserFragment : Fragment(), UserViewModelCallback {
    private lateinit var binding : FragmentGetUserBinding
    private var userVM : UserViewModel? = null
    private lateinit var application: Application
    private lateinit var userRepo : UserRepository
    var arrayUser : List<User> = ArrayList()
    private lateinit var adapter: UserAdapter


    @InternalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_get_user, container, false)
        val view : View =  binding.root
        application = this.requireActivity().application
        adapter = UserAdapter(viewLifecycleOwner)
        userRepo = UserRepository(this.application)

        adapter.arrUsers = arrayUser;

        binding.revFraggetuserUser.adapter = adapter
        binding.revFraggetuserUser.layoutManager = LinearLayoutManager(context)
        adapter.notifyDataSetChanged()
        userVM = UserViewModel()
        userVM!!.callBack = this
        userVM!!.getAllUser()

        saveListUsers()
        readListUsersSaved()

        return view
    }

    private fun readListUsersSaved() {
        binding.buttonFraggetuserRead.setOnClickListener {
            val action = GetUserFragmentDirections.actionGetUserFragmentToSaveUsersFragment()
            findNavController().navigate(action)
        }
    }

    @InternalCoroutinesApi
    private fun saveListUsers() {
        binding. buttonFraggetuserSave.setOnClickListener {
            GlobalScope.async (Dispatchers.IO) {
                userRepo.addListPeople(arrayUser)
            }
            Toast.makeText(context, "saved to Room DB success!", Toast.LENGTH_LONG).show()
        }
    }

    override fun getListUser(item: ArrayList<User>) {
        adapter.arrUsers = item;
        arrayUser = item
        adapter.notifyDataSetChanged();
    }


}