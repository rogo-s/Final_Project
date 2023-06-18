package com.rogo.final_project.view.fragment

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
import com.rogo.final_project.databinding.FragmentSendOtpBinding
import com.rogo.final_project.view.model.data.DataOtp
import com.rogo.final_project.view.model.data.DataResendOtp
import com.rogo.final_project.viewmodel.UserViewModel
//import com.rogo.final_project.view.model.data.DataOtp
//import com.rogo.final_project.viewmodel.VerifyViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SendOtpFragment : Fragment() {
    lateinit var binding : FragmentSendOtpBinding
    lateinit var sharedPreferences: SharedPreferences
    private lateinit var userVm : UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSendOtpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences = requireContext().getSharedPreferences("Regist", Context.MODE_PRIVATE)
        var getEmail = sharedPreferences.getString("email", "")

        userVm = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.email.text = "ke $getEmail"

        binding.verifyOtp.setOnClickListener{
            verifyOtp()
        }

        binding.txtNewOtp.setOnClickListener {
            resendOtp()
        }
    }

    private fun verifyOtp() {
        val otp = binding.sendOtp.text.toString()
        userVm = ViewModelProvider(this).get(UserViewModel::class.java)
        val email = sharedPreferences.getString("email", "")
        userVm.verifyOtpRequest(DataOtp(email!!, otp.toInt()))
        userVm.userOtp.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "${it!!.message}", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_sendOtpFragment_to_loginFragment)
        }
    }

    fun resendOtp(){
        var email = sharedPreferences.getString("email", "")
        userVm.resendOtpRequest(DataResendOtp(email!!))
        userVm.userResendOto.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(), "${it!!.message}", Toast.LENGTH_SHORT).show()
        }
    }
}