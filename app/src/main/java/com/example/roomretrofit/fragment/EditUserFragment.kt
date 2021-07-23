package com.example.roomretrofit.fragment

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.roomretrofit.R
import com.example.roomretrofit.database.UserRepository
import com.example.roomretrofit.databinding.FragmentEditUserBinding
import com.example.roomretrofit.entity.User
import kotlinx.coroutines.*

class  EditUserFragment : Fragment() {
    private val args  by navArgs<EditUserFragmentArgs>()
    private lateinit var editBinding : FragmentEditUserBinding
    private lateinit var userRepo : UserRepository
    private lateinit var application: Application

    @InternalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        editBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_edit_user, container, false )
        val view : View = editBinding.root
        application = this.requireActivity().application

        setupView(args.user)
        updateUser(view)
        cancelEdit(view)

        return view
    }


    private fun cancelEdit(view: View) {
        editBinding.buttonEditBack.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.backToSaveUsersFragment)
        }
    }


    @InternalCoroutinesApi
    private fun updateUser(view : View) {
        editBinding.buttonEditUpdate.setOnClickListener {
            val name : String = editBinding.edittextEditName.text.toString().trim()
            val type : String = editBinding.edittextEditType.text.toString().trim()
            val url : String = editBinding.edittextEditUrl.text.toString().trim()
            val id : Long = args.user.idUser

            if (name.isEmpty() || type.isEmpty() || url.isEmpty()) {
                Toast.makeText(context, "Re u blind?? Need to fill all", Toast.LENGTH_LONG).show()
            } else {
                userRepo = UserRepository(this.requireActivity().application)
                val userUpdated = User(id, name, type, url)
                GlobalScope.launch (Dispatchers.IO) {
                    userRepo.updateUser(userUpdated)
                }
                Toast.makeText(context, "O. ke r do' bro", Toast.LENGTH_LONG).show()
                findNavController().popBackStack(R.id.editUserFragment, true)
            }
        }
    }

    private fun setupView(user: User) {
        editBinding.edittextEditName.setText(user.login)
        editBinding.edittextEditType.setText(user.type)
        editBinding.edittextEditUrl.setText(user.url)
    }

}