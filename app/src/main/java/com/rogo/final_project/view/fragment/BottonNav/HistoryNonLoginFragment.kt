package com.rogo.final_project.view.fragment.BottonNav

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.rogo.final_project.R
import com.rogo.final_project.databinding.FragmentHistoryNonLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryNonLoginFragment : Fragment() {

    lateinit var sharedPref: SharedPreferences
    lateinit var binding : FragmentHistoryNonLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHistoryNonLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        login()
    }
    private fun login() {
        binding.btnLogin.setOnClickListener {
            view?.post {
                findNavController().navigate(R.id.action_historyNonLoginFragment2_to_loginFragment2)
            }
        }
    }

    private fun isLogin() {
        sharedPref = requireContext().getSharedPreferences("dataUser", Context.MODE_PRIVATE)
        if (sharedPref.getString("token", "")!!.isEmpty()) {
            binding.layoutloginHistori.visibility = View.GONE
            binding.layoutNoLoginHistori.visibility = View.VISIBLE
        } else {
            binding.layoutloginHistori.visibility = View.VISIBLE
            binding.layoutNoLoginHistori.visibility = View.GONE
        }
    }

    override fun onResume() {
        super.onResume()
        isLogin()
    }
}