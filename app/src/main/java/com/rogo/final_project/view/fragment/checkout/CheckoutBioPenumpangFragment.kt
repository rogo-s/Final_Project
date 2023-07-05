package com.rogo.final_project.view.fragment.checkout

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.rogo.final_project.R
import com.rogo.final_project.databinding.FragmentCheckoutBioPenumpangBinding
import com.rogo.final_project.databinding.FragmentCheckoutPenumpangBinding
import com.rogo.final_project.view.model.data.checkout.CreateCheckout
import com.rogo.final_project.viewmodel.CheckoutViewModel
import com.rogo.final_project.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CheckoutBioPenumpangFragment : Fragment() {

    lateinit var binding : FragmentCheckoutBioPenumpangBinding
    lateinit var sharedPref : SharedPreferences
    private lateinit var checkoutViewModel : CheckoutViewModel
    private val homeViewModel : HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCheckoutBioPenumpangBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPref = requireContext().getSharedPreferences("dataUser", Context.MODE_PRIVATE)

        checkoutViewModel = ViewModelProvider(this).get(CheckoutViewModel::class.java)

        binding.btnSave.setOnClickListener {
            dataPenumpang()
        }
//        val title = resources.getStringArray(R.array.title)
//        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, title)
//        binding.auto.setAdapter(arrayAdapter)
        binding.btnSwitch.setOnCheckedChangeListener{ p0, isChecked ->
            if (isChecked){
                binding.txtNameFam.visibility = View.VISIBLE
                binding.etNameFam.visibility = View.VISIBLE
            }else{
                binding.txtNameFam.visibility = View.GONE
                binding.etNameFam.visibility = View.GONE
            }
        }
    }

    fun dataPenumpang(){
        val ticketId = homeViewModel.getTicketId().toString()
        val idDepature = homeViewModel.getIdDep().toString()
        val idReturn = homeViewModel.getIdReturn().toString()
        val id = arguments?.getInt("id")
        val accessToken = sharedPref.getString("token", "").toString()
        val dewasa = homeViewModel.getPenumpangDewasa()
        val anak = homeViewModel.getPenumpangAnak()
        val bayi = homeViewModel.getPenumpangBayi()
        val total = dewasa + anak + bayi
        val name = binding.etName.text.toString()
        val nameFam = binding.etNameFam.text.toString()
        val phoneNumber = sharedPref.getString("telephone", "")
        val seat = binding.etSeat.text.toString()
        val returnSeat = binding.etSeat.text.toString()
        val email = sharedPref.getString("email", "")
//        val price = homeViewModel.getorder()!!.toInt()
        val dataCheckout = CreateCheckout(seat, email!!, nameFam, name,true, "data",
            phoneNumber!!, 5950000, returnSeat, "$idDepature, $idReturn", total)
        checkoutViewModel.checkoutUser(accessToken, dataCheckout)
        checkoutViewModel.dataCheckout.observe(viewLifecycleOwner) {
            if (it != null) {
                Toast.makeText(requireContext(), "Data Checkout Tersimpan", Toast.LENGTH_SHORT).show()
//                val sPref = sharedPref.edit()
//                sPref.putString("total", total!!.toInt())
//                sPref.putString("telephone", phoneNumber)
//                sPref.apply()
                findNavController().navigate(R.id.action_checkoutBioPenumpangFragment_to_checkoutFragment)
            } else {
                Toast.makeText(requireContext(), "Data Checkout Tidak Tersimpan", Toast.LENGTH_SHORT).show()
            }
        }
    }
}