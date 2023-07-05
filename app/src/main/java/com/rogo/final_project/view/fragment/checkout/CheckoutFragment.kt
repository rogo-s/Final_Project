package com.rogo.final_project.view.fragment.checkout

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.rogo.final_project.R
import com.rogo.final_project.databinding.FragmentCheckoutBinding
import com.rogo.final_project.util.Utill
import com.rogo.final_project.viewmodel.DetailViewModel
import com.rogo.final_project.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CheckoutFragment : Fragment() {
    lateinit var binding : FragmentCheckoutBinding
    private lateinit var DetailVm : DetailViewModel
    private val homeViewModel : HomeViewModel by viewModels()
    lateinit var sharedPref : SharedPreferences


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCheckoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPref = requireContext().getSharedPreferences("dataUser", Context.MODE_PRIVATE)

        DetailVm = ViewModelProvider(this).get(DetailViewModel::class.java)


        val idSingle = homeViewModel.getTicketId()
        DetailVm.getdetailticket(idSingle!!)

        binding.btnSave.setOnClickListener {
            homeViewModel.saveIdTicket(id!!)
            if (sharedPref.getString("token", "").toString().isNotEmpty()) {
                if (findNavController().currentDestination!!.id == R.id.checkoutFragment) {

                    findNavController().navigate(R.id.action_checkoutFragment_to_paymentFragment)
                }

            } else {
                val fragId = findNavController().currentDestination?.id
                findNavController().popBackStack(fragId!!, true)
                findNavController().navigate(R.id.bottomSheetCheckoutFragment)
            }
        }

        DetailVm.livedetailticket.observe(viewLifecycleOwner) { detail ->

            binding.apply {
                val departureTime = detail!!.ticket.flight.departureTime
                val arrivalTime = detail.ticket.flight.arrivalTime
                val depatureAirport = detail.ticket.flight.departureAirport
                val arrivalAirport = detail.ticket.flight.arrivalAirport
                val baggage = detail.ticket.additionalInformation
                val flightCode = detail.ticket.flight.flightCode
                val dateDepature = detail.ticket.flight.departureDate
                val arrivalDate = detail.ticket.flight.arrivalDate
                val depatureCity = detail.ticket.flight.departureCity
                val arrivalCity = detail.ticket.flight.arrivalCity
                val classSeat = detail.ticket.classSeat
                val hargatotal = detail.ticket.price

                val dewasa = homeViewModel.getPenumpangDewasa()
                val anak = homeViewModel.getPenumpangAnak()
                val bayi = homeViewModel.getPenumpangBayi()
                val total = dewasa + anak + bayi

                kedatangan.text = arrivalTime
                keberangkatan.text = departureTime

                keberangkatanAirport.text = depatureAirport
                kedatanganAirport.text = arrivalAirport
                txtAsal.text = depatureCity
                txtTujuan.text = arrivalCity
                tglKeberangkatan.text = dateDepature
                tglKedatangan.text = arrivalDate
                noPesawat.text = flightCode
                bagasi.text = baggage
                kelasSeat.text = classSeat


                harga.text = hargatotal.toString()
                totalPrice.text = hargatotal.toString()
                jumlah.text = "$total Passangers"

            }
        }
    }
}