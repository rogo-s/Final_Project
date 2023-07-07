package com.rogo.final_project.view.fragment.BottonNav

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rogo.final_project.R
import com.rogo.final_project.databinding.FragmentNotifBinding
import com.rogo.final_project.view.adapter.NotifAdapter
import com.rogo.final_project.viewmodel.NotifikasiViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class NotifFragment : Fragment() {

    lateinit var binding : FragmentNotifBinding
    lateinit var sharedPref: SharedPreferences
    private lateinit var notifAdapter: NotifAdapter
    lateinit var notifVM : NotifikasiViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNotifBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        login()

        notifVM = ViewModelProvider(this).get(NotifikasiViewModel::class.java)

//        notifAdapter = NotifAdapter(ArrayList())
//

    }

    private fun login() {
        binding.btnMasuk.setOnClickListener {
            view?.post {
                findNavController().navigate(R.id.action_notifFragment2_to_loginFragment)
            }
        }
    }

    private fun isLogin() {
        sharedPref = requireContext().getSharedPreferences("dataUser", Context.MODE_PRIVATE)
        Log.d("Berhasil", sharedPref.getString("token", " ").toString())
        Log.d("Berhasil", sharedPref.getString("refreshToken", " ").toString())
        if (sharedPref.getString("token", "").toString().isNotEmpty()) {
            binding.akunLogin.visibility = View.VISIBLE
            getDataNotif()
            Log.d("Berhasil Login", "berhasil")
            binding.akunNonLogin.visibility = View.GONE

        } else {
            binding.akunNonLogin.visibility = View.VISIBLE
            binding.akunLogin.visibility = View.GONE
        }
    }

    fun getDataNotif(){
        val token = sharedPref.getString("token", "").toString()
        notifVM.getNotif(token)
        Log.d("historytoken", token)
        notifVM = ViewModelProvider(this).get(NotifikasiViewModel::class.java)
        notifAdapter = NotifAdapter(emptyList())
        val layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL,false)
        binding.rvNotif.layoutManager = layoutManager
        binding.rvNotif.adapter = notifAdapter
        notifVM.getNotifikasi.observe(viewLifecycleOwner) {
            if (it != null) {
                notifAdapter.setDataNotif(it.data)
                notifAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        isLogin()
    }


}