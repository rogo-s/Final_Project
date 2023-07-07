package com.rogo.final_project.view.fragment.BottonNav

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rogo.final_project.R
import com.rogo.final_project.databinding.FragmentHistoryNonLoginBinding
import com.rogo.final_project.view.Adapter.RiwayatAdapter
import com.rogo.final_project.view.model.data.search.Data
import com.rogo.final_project.view.model.data.search.Flight
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryNonLoginFragment : Fragment() {

    lateinit var sharedPref: SharedPreferences
    lateinit var binding : FragmentHistoryNonLoginBinding
    private lateinit var riwayatAdapter: RiwayatAdapter
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

            val layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL,false)
            binding.rvHasilPencarian.layoutManager = layoutManager
            val itemHistory = listOf(
                Flight("Super Air Jet","Juanda International Airport","Surabaya","2023-07-06","08:00:00:00","5 Maret 2023","Bandara Soekarno Hatta","Jakarta","2023-07-06","07:00:00:00", "04:00", "923103", 2, ""),
                Flight("Super Air Jet","Bandar Udara Internasional Kualanamu","Medan","2023-07-06","08:00:00:00","5 Maret 2023","Juanda International Airport","Surabaya","2023-07-06","07:00:00:00", "04:00", "923103", 2, ""),
            )
            riwayatAdapter = RiwayatAdapter(itemHistory)
            binding.rvHasilPencarian.adapter = riwayatAdapter

        } else {
            binding.layoutNoLoginHistori.visibility = View.GONE
            binding.layoutloginHistori.visibility = View.VISIBLE
        }
    }

    override fun onResume() {
        super.onResume()
        isLogin()
    }
}