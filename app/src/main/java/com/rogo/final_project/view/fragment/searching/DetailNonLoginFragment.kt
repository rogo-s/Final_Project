package com.rogo.final_project.view.fragment.searching

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.rogo.final_project.databinding.FragmentDetailNonLoginBinding
import com.rogo.final_project.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailNonLoginFragment : Fragment() {
    private lateinit var binding : FragmentDetailNonLoginBinding
    private lateinit var DetailVm : DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailNonLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        DetailVm = ViewModelProvider(this).get(DetailViewModel::class.java)

        val id = arguments?.getInt("id")
        Log.d("Detail Fragment", "id = $id")
        DetailVm.getdetailticket(id!!)

        DetailVm.livedetailticket.observe(viewLifecycleOwner) {detail->
            val getdetail = detail.ticket.flight
//            val id = getdetail.id.toString()
//            val bundle = Bundle()
//            bundle.getString("id", id)
            if (getdetail != null){
                binding.layoutDetail.visibility = View.VISIBLE
                binding.totalTicket.visibility = View.VISIBLE
                Log.d("DetailPenerbangan","Berhasil dong")
                binding.apply {
                    nomorseri.visibility = View.GONE
                    val idpenerbangan = detail.ticket.id
                    idFlight.text = idpenerbangan.toString()
                    tvDepartureAirport.text = getdetail.departureAirport
                    tvDateDeparture.text = getdetail.departureDate
                    tvTimeDeparture.text = getdetail.departureTime
                    tvFlightAsal.text = getdetail.departureCity
                    tvFlightDestination.text = getdetail.arrivalCity
//                    baggage.text = getdetail.plane.baggageMaxCapacity.toString()
//                    cabinbaggage.text = getdetail
                    tvTimeArrive.text = getdetail.arrivalTime
                    tvDateArrive.text = getdetail.arrivalDate
                    tvArriveAirport.text = getdetail.arrivalAirport
//                    val price = Utill.getPriceIdFormat(getdetail.provTotalPrice)
//                    tvPriceTicket.text = "$price"

//                    binding.btnSelectFlight.setOnClickListener {
//                        findNavController().navigate(R.id.action_detailNonLoginFragment_to_checkoutBioPemesanFragment)
//                    }


                }
            }else {
                Log.e("DetailPenerbangan", "detailTicket is null")
            }
        }
    }

}