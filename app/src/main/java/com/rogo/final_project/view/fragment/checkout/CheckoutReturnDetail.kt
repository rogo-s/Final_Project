package com.rogo.final_project.view.fragment.checkout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.rogo.final_project.R
import com.rogo.final_project.databinding.FragmentCheckoutReturnDetailBinding
import com.rogo.final_project.viewmodel.DetailViewModel
import com.rogo.final_project.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CheckoutReturnDetail : Fragment() {
    lateinit var binding: FragmentCheckoutReturnDetailBinding
    private lateinit var DetailVm : DetailViewModel
    lateinit var homeVm : HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCheckoutReturnDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        DetailVm = ViewModelProvider(this).get(DetailViewModel::class.java)
        homeVm = ViewModelProvider(this).get(HomeViewModel::class.java)

        val idReturn = homeVm.getIdReturn()
        DetailVm.getdetailticket(idReturn!!)

        DetailVm.livedetailticket.observe(viewLifecycleOwner) {detail->
            binding.apply {
                val departureTime = detail!!.ticket.flight.departureTime
                val arrivalTime = detail.ticket.flight.arrivalTime
                val getPrice = detail.ticket.price
                val depatureAirport = detail.ticket.flight.departureAirport
                val arrivalAirport = detail.ticket.flight.arrivalAirport
                val baggage = detail.ticket.additionalInformation
                val flightCode = detail.ticket.flight.flightCode
                val dateDepature = detail.ticket.flight.departureDate
                val arrivalDate = detail.ticket.flight.arrivalDate
                val depatureCity = detail.ticket.flight.departureCity
                val arrivalCity = detail.ticket.flight.arrivalCity
                val classSeat = detail.ticket.classSeat

                tvJamDatang.text = arrivalTime
                tvJamBerangkat.text = departureTime
//                tvPriceTicket.text = getPrice.toString()
//                tvBandaraDatang.text = depatureAirport
                tvBandara.text = depatureCity
                tvBandaraDatang.text = arrivalCity
                tvTanggalBerangkat.text = dateDepature
                tvTanggalDatang.text = arrivalDate
                tvBookingCode.text = flightCode
                informasi.text = baggage
                tvharga.text = "Harga Tiket : $getPrice"


//                KelasSeat.text = classSeat

            }

        }
    }


}