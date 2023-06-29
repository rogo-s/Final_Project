package com.rogo.final_project.view.fragment.Auth

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.rogo.final_project.R
import com.rogo.final_project.databinding.FragmentLoginBinding
import com.rogo.final_project.view.model.data.login.DataLogin
import com.rogo.final_project.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    lateinit var binding : FragmentLoginBinding
    private lateinit var sharedPref : SharedPreferences
    private lateinit  var loginViewModel : UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        sharedPref = requireContext().getSharedPreferences("dataUser", Context.MODE_PRIVATE)


        binding.btnLogin.setOnClickListener {
            doLogin()
        }

        binding.btnSignup.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        binding.forgotPass.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_forgotPasswordFragment)
        }

    }


    private fun doLogin(){
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        val dataEmail = sharedPref.getString("email", "")
        val dataPass = sharedPref.getString("password", "")
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(requireContext(), "Please fill all the field", Toast.LENGTH_SHORT).show()
        } else {
            loginViewModel.loginDataUser(DataLogin(email, password))
            loginViewModel.usersLogin.observe(viewLifecycleOwner){
                if (it != null) {
                    val sPref = sharedPref.edit()
                    sPref.putString("token", it.accessToken)
                    Log.d("HomeFragment", "token: ${it.accessToken}")
                    sPref.apply()
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment2)
                } else if (email != dataEmail) {
                    Toast.makeText(requireContext(), "Email tidak terdaftar", Toast.LENGTH_SHORT).show()
                } else if (password != dataPass) {
                    Toast.makeText(requireContext(), "Maaf, kata sandi salah", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}