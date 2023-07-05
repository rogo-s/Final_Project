package com.rogo.final_project.view.fragment.checkout

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
import com.rogo.final_project.databinding.FragmentCheckoutBioBinding
import com.rogo.final_project.view.model.data.profile.UpdateProfile
import com.rogo.final_project.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CheckoutBioFragment : Fragment() {

    lateinit var binding : FragmentCheckoutBioBinding
    lateinit var sharedPref: SharedPreferences
    private lateinit  var bioPemesanViewModel : UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCheckoutBioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bioPemesanViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        sharedPref = requireContext().getSharedPreferences("dataUser", Context.MODE_PRIVATE)

        binding.btnSave.setOnClickListener {
            dataPemesanan()
        }

        binding.btnSaveRountrip.setOnClickListener {
            dataPemesananRound()
        }

        binding.btnSwitch.setOnCheckedChangeListener{ p0, isChecked ->
            if (isChecked){
                binding.txtNameFam.visibility = View.VISIBLE
                binding.etNameFam.visibility = View.VISIBLE
            }else{
                binding.txtNameFam.visibility = View.GONE
                binding.etNameFam.visibility = View.GONE
            }
        }

//        binding.btnSwitchDua.setOnCheckedChangeListener{ p0, isChecked ->
//            if (isChecked){
//                binding.btnSaveRountrip.visibility = View.VISIBLE
//                binding.btnSave.visibility = View.GONE
//            }else{
//                binding.btnSaveRountrip.visibility = View.GONE
//                binding.btnSave.visibility = View.VISIBLE
//            }
//        }
    }

    private fun dataPemesanan() {
        val name = binding.etName.text.toString()
        val phoneNumber = binding.etPhoneCheck.text.toString()
        val accessToken = sharedPref.getString("token", "").toString()
        val dataProfile = UpdateProfile(name, phoneNumber)
        bioPemesanViewModel.updateDataProfile(accessToken, dataProfile)
        bioPemesanViewModel.profileUpdate.observe(viewLifecycleOwner){
            if (it != null) {
                Toast.makeText(requireContext(), "Simpan Data Pemesanan Berhasil", Toast.LENGTH_SHORT).show()
                val sPref = sharedPref.edit()
                sPref.putString("name", name)
                sPref.putString("telephone", phoneNumber)
                sPref.apply()
                findNavController().navigate(R.id.action_checkoutBioFragment_to_checkoutPenumpangFragment)
            }
        }
    }

    private fun dataPemesananRound() {
        val name = binding.etName.text.toString()
        val phoneNumber = binding.etPhoneCheck.text.toString()
        val accessToken = sharedPref.getString("token", "").toString()
        val dataProfile = UpdateProfile(name, phoneNumber)
        bioPemesanViewModel.updateDataProfile(accessToken, dataProfile)
        bioPemesanViewModel.profileUpdate.observe(viewLifecycleOwner){
            if (it != null) {
                Toast.makeText(requireContext(), "Simpan Data Pemesanan Berhasil", Toast.LENGTH_SHORT).show()
                val sPref = sharedPref.edit()
                sPref.putString("name", name)
                sPref.putString("telephone", phoneNumber)
                sPref.apply()
                findNavController().navigate(R.id.action_checkoutBioFragment_to_checkoutBioPenumpangFragment)
            }
        }
    }
}