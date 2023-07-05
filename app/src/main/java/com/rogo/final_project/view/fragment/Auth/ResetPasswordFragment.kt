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
import com.rogo.final_project.databinding.FragmentResetPasswordBinding
import com.rogo.final_project.view.model.data.repass.ResetPass
import com.rogo.final_project.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResetPasswordFragment : Fragment() {
    lateinit var binding : FragmentResetPasswordBinding
    private lateinit var sharedPref : SharedPreferences
    lateinit var resetPassViewModel : UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentResetPasswordBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        resetPassViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        sharedPref = requireContext().getSharedPreferences("dataUser", Context.MODE_PRIVATE)

        binding.btnSimpan.setOnClickListener {
            resetPass()
        }
    }

    fun resetPass() {
        resetPassViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        val email = sharedPref.getString("email", "")
        val confirm = binding.etKode.text.toString()
        val password = binding.etPassword.text.toString()
        val rePassword = binding.etRePassword.text.toString()
        if (password.length >= 8) {
            val dataResetPass = ResetPass(email!!, rePassword, password, confirm)
            resetPassViewModel.resetPassword(dataResetPass)
            resetPassViewModel.resetPass.observe(viewLifecycleOwner) {
                if (it != null) {
                    val sPref = sharedPref.edit()
                    sPref.putString("confirmation", rePassword)
                    sPref.putString("password", password)
                    sPref.putString("token", confirm)
                    sPref.apply()

                    findNavController().navigate(R.id.action_resetPasswordFragment_to_loginFragment)
                    Toast.makeText(requireContext(), "Reset password berhasil!", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            Toast.makeText(requireContext(), "Password minimal 8 karakter!", Toast.LENGTH_SHORT).show()
        }
    }
}
