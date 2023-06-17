package com.rogo.final_project.view.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.rogo.final_project.R
import com.rogo.final_project.databinding.FragmentRegisterBinding
import com.rogo.final_project.view.model.data.User
import com.rogo.final_project.viewmodel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {
    lateinit var binding : FragmentRegisterBinding
    private val registerViewModel : RegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRegist.setOnClickListener {
            val name = binding.etNameReg.text.toString()
            val email = binding.etEmailReg.text.toString()
            val phoneNumber = binding.etPhoneReg.text.toString()
            val password = binding.etPassReg.text.toString()
            if (password.length >= 8) {
                doRegist(User(name, email, password, "firstname", "lastname", phoneNumber))
            } else {
                Toast.makeText(requireContext(), "Password min 8 karakter!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnSignin.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
    }

    fun doRegist(data : User) {
        registerViewModel.registDataUser(data)
        registerViewModel.usersRegist.observe(viewLifecycleOwner) {
            if (it != null) {
                Toast.makeText(requireContext(), "Registrasi Berhasil", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Registrasi Gagal", Toast.LENGTH_SHORT).show()
            }
        }
    }
}