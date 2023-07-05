package com.rogo.final_project.view.fragment.BottonNav

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.rogo.final_project.R
import com.rogo.final_project.databinding.FragmentAkunNonLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AkunNonLoginFragment : Fragment() {
    lateinit var binding : FragmentAkunNonLoginBinding
    lateinit var sharedPref: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAkunNonLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        login()

//        binding.btnMasuk.setOnClickListener {
//            findNavController().navigate(R.id.action_akunNonLoginFragment2_to_loginFragment)
//        }

        binding.editProfile.setOnClickListener {
            findNavController().navigate(R.id.action_akunNonLoginFragment2_to_editProfileFragment)
        }

        binding.keluar.setOnClickListener {
            logout()
        }

//        binding.bt.setOnClickListener {
//            pref.edit().clear().apply()
//            Log.d("DataToken", pref.getString("token", "").toString())
//            findNavController().navigate(R.id.action_profileFragment2_to_homeFragment2)
//
//        }
    }

    private fun login() {
        binding.btnMasuk.setOnClickListener {
            view?.post {
                findNavController().navigate(R.id.action_akunNonLoginFragment2_to_loginFragment)
            }
        }
        binding.editProfile.setOnClickListener {
            findNavController().navigate(R.id.action_akunNonLoginFragment2_to_editProfileFragment)
        }
    }

//    private fun isLogin() {
//        sharedPref = requireContext().getSharedPreferences("LOGIN", Context.MODE_PRIVATE)
//        if (sharedPref.getString("token", "")!!.isEmpty()) {
//            binding.akunLogin.visibility = View.VISIBLE
//            binding.akunNonLogin.visibility = View.GONE
//        } else {
//            binding.akunLogin.visibility = View.GONE
//            binding.akunNonLogin.visibility = View.VISIBLE
//        }
//    }

    private fun isLogin() {
        sharedPref = requireContext().getSharedPreferences("dataUser", Context.MODE_PRIVATE)
        Log.d("Berhasil", sharedPref.getString("token", " ").toString())
        Log.d("Berhasil", sharedPref.getString("refreshToken", " ").toString())
        if (sharedPref.getString("token", "").toString().isNotEmpty()) {
            binding.akunLogin.visibility = View.VISIBLE
            Log.d("Berhasil Login", "berhasil")
            binding.akunNonLogin.visibility = View.GONE

        } else {
            binding.akunNonLogin.visibility = View.VISIBLE
            binding.akunLogin.visibility = View.GONE
        }
    }

    fun logout(){
        val logoutUser = sharedPref.edit()
        logoutUser.clear()
        logoutUser.apply()
        Log.d("DataToken", sharedPref.getString("token", "").toString())
        findNavController().navigate(R.id.action_akunNonLoginFragment2_to_homeFragment2)
    }

    override fun onResume() {
        super.onResume()
        isLogin()
    }
}