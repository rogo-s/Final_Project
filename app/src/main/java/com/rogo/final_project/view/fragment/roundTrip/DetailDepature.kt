package com.rogo.final_project.view.fragment.roundTrip

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.rogo.final_project.R
import com.rogo.final_project.databinding.FragmentDetailDepatureBinding
import com.rogo.final_project.viewmodel.DetailViewModel
import com.rogo.final_project.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailDepature : Fragment() {
    lateinit var binding: FragmentDetailDepatureBinding
    private lateinit var DetailVm : DetailViewModel
    lateinit var homeVm : HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailDepatureBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        DetailVm = ViewModelProvider(this).get(DetailViewModel::class.java)
        homeVm = ViewModelProvider(this).get(HomeViewModel::class.java)

        val idDeparture = homeVm.getIdDep()
        DetailVm.getdetailticket(idDeparture!!)

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
//                KelasSeat.text = classSeat

            }
//            val getdetail = detail.ticket.flight
//            val id = getdetail.id.toString()
//            val bundle = Bundle()
//            bundle.getString("id", id)
//            if (getdetail != null){
//                binding.layoutDetail.visibility = View.VISIBLE
//                binding.totalTicket.visibility = View.VISIBLE
//                Log.d("DetailPenerbangan","Berhasil dong")
//                binding.apply {
//                    nomorseri.visibility = View.GONE
//                    val idpenerbangan = detail.ticket
//                    idFlight.text = idpenerbangan.toString()
//                    tvDepartureAirport.text = getdetail.departureAirport
//                    tvDateDeparture.text = getdetail.departureDate
//                    tvTimeDeparture.text = getdetail.departureTime
//                    tvFlightAsal.text = getdetail.departureCity
//                    tvFlightDestination.text = getdetail.arrivalCity
////                    baggage.text = getdetail.plane.baggageMaxCapacity.toString()
////                    cabinbaggage.text = getdetail
//                    tvTimeArrive.text = getdetail.arrivalTime
//                    tvDateArrive.text = getdetail.arrivalDate
//                    tvArriveAirport.text = getdetail.arrivalAirport
////                    val price = Utill.getPriceIdFormat(getdetail.provTotalPrice)
////                    tvPriceTicket.text = "$price"
//
////                    binding.btnSelectFlight.setOnClickListener {
////                        findNavController().navigate(R.id.action_detailNonLoginFragment_to_checkoutBioPemesanFragment)
////                    }
//
//
//                }
//            }else {
//                Log.e("DetailPenerbangan", "detailTicket is null")
//            }
        }
    }


}