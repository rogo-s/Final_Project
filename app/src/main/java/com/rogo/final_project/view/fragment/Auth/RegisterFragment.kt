package com.rogo.final_project.view.fragment.Auth

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.rogo.final_project.R
import com.rogo.final_project.databinding.FragmentRegisterBinding
import com.rogo.final_project.view.model.data.register.DataRegist
import com.rogo.final_project.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {
    lateinit var binding : FragmentRegisterBinding
    private lateinit var registerViewModel : UserViewModel
    private lateinit var pref: SharedPreferences

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
        registerViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        pref = requireContext().getSharedPreferences("dataUser", Context.MODE_PRIVATE)

        binding.btnRegist.setOnClickListener {
            register()
        }

        binding.btnSignin.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }


//        binding.btnRegist.setOnClickListener {
//            val name = binding.etNameReg.text.toString()
//            val email = binding.etEmailReg.text.toString()
//            val phoneNumber = binding.etPhoneReg.text.toString()
//            val password = binding.etPassReg.text.toString()
//            if (password.length >= 8) {
//                doRegist(DataRegist(email, "", "", name, password, phoneNumber))
//            } else {
//                Toast.makeText(requireContext(), "Password min 8 karakter!", Toast.LENGTH_SHORT).show()
//            }
//        }

    }

    fun register () {
        val email = binding.etEmailReg.text.toString()
        val name = binding.etNameReg.text.toString()
        val password = binding.etPassReg.text.toString()
        val phoneNumber = binding.etPhoneReg.text.toString()

        if (email.isEmpty() || name.isEmpty() || password.isEmpty() || phoneNumber.isEmpty()) {
            Toast.makeText(requireContext(), "Please fill all the field", Toast.LENGTH_SHORT).show()
        } else {
            registerViewModel.registDataUser(DataRegist(email, name, password, phoneNumber))
            registerViewModel.usersRegist.observe(viewLifecycleOwner) {
                        val sharedPref = pref.edit()
                        sharedPref.putString("email", email)
                        sharedPref.putString("name", name)
                        sharedPref.putString("telephone", phoneNumber)
                        sharedPref.apply()
                        findNavController().navigate(R.id.action_registerFragment_to_sendOtpFragment)
                    }
                }
            }
        }

//    fun doRegist(data : DataRegist) {
//        registerViewModel.registDataUser(data)
//        registerViewModel.usersRegist.observe(viewLifecycleOwner) {
//            if (it != null) {
//                Toast.makeText(requireContext(), "Registrasi Berhasil", Toast.LENGTH_SHORT).show()
//                findNavController().navigate(R.id.action_registerFragment_to_sendOtpFragment)
//            } else {
//                Toast.makeText(requireContext(), "Registrasi Gagal", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }