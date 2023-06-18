package com.rogo.final_project.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.rogo.final_project.R
import com.rogo.final_project.databinding.FragmentLoginBinding
import com.rogo.final_project.view.model.data.DataLogin
import com.rogo.final_project.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    lateinit var binding : FragmentLoginBinding
    private val loginViewModel : UserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            doLogin(DataLogin(email, password))
        }

        binding.btnSignup.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        binding.forgotPass.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_resetPasswordFragment)
        }
    }

    fun doLogin(data : DataLogin){
        loginViewModel.loginDataUser(data)
        loginViewModel.usersLogin.observe(viewLifecycleOwner){
            if (it != null) {
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment2)
                Toast.makeText(requireContext(), "Login Berhasil", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Login Gagal", Toast.LENGTH_SHORT).show()
            }
        }

    }

}