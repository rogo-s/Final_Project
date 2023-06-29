package com.rogo.final_project.view.fragment.Auth

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.rogo.final_project.R
import com.rogo.final_project.databinding.FragmentForgotPasswordBinding
import com.rogo.final_project.view.model.data.repass.ForgotPass
import com.rogo.final_project.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForgotPasswordFragment : Fragment() {

    lateinit var binding : FragmentForgotPasswordBinding
    private lateinit var sharedPref : SharedPreferences
    private val forgotPassViewModel : UserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentForgotPasswordBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPref = requireContext().getSharedPreferences("dataUser", Context.MODE_PRIVATE)

        binding.btnKirim.setOnClickListener {
            forgotPass()
        }
    }

    fun forgotPass(){
        val email = binding.etEmail.text.toString()
        forgotPassViewModel.forgotPassword(ForgotPass(email))
        forgotPassViewModel.forgotPass.observe(viewLifecycleOwner){
            val sPref = sharedPref.edit()
            sPref.putString("email", email)
            sPref.apply()
            findNavController().navigate(R.id.action_forgotPasswordFragment_to_resetPasswordFragment)
            Toast.makeText(requireContext(), "Tautan reset password terkirim", Toast.LENGTH_SHORT).show()
        }
    }
}