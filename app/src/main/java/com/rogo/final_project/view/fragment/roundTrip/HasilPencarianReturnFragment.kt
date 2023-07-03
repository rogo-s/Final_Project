package com.rogo.final_project.view.fragment.roundTrip

import android.app.DatePickerDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rogo.final_project.R
import com.rogo.final_project.databinding.FragmentHasilPencarianReturnBinding
import com.rogo.final_project.view.Adapter.HasilPencarianAdapter
import com.rogo.final_project.view.Adapter.RoundAdapter
import com.rogo.final_project.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class HasilPencarianReturnFragment : Fragment() {
    lateinit var binding: FragmentHasilPencarianReturnBinding
    private val hmVm: HomeViewModel by viewModels()
    lateinit var fromPref: SharedPreferences
    private lateinit var searchAdapter: HasilPencarianAdapter
    private var tanggalPergi: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHasilPencarianReturnBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fromPref = requireContext().getSharedPreferences("keyFrom", Context.MODE_PRIVATE)
        val cityDepature = fromPref.getString("departureCity", "")

        fromPref = requireContext().getSharedPreferences("keyTo", Context.MODE_PRIVATE)
        val cityArrival = fromPref.getString("arrivalCity", "")

        val cityFrom = hmVm.getCityFrom()
        val cityTo = hmVm.getCityTo()
        val dewasa = hmVm.getPenumpangDewasa()
        val departure = hmVm.getDepartureDate()
        val arrived = hmVm.getArrivedDate()
        val anak = hmVm.getPenumpangAnak()
        val bayi = hmVm.getPenumpangBayi()
        val totalPassengers = dewasa + anak + bayi

        binding.toolbarTitle.text = "$cityFrom < > $cityTo - $totalPassengers Penumpang "

//        returnOnly(cityFrom, cityTo, departure,arrived)

        dateToolbarDepature(departure)
        dateToolbarReturn(arrived)
        returnOnly(cityTo,cityFrom, departure,arrived)
    }

    private fun dateToolbarDepature(dateDeparture: String?) {
        if (dateDeparture != null) {
            binding.etDate.setText(dateDeparture)
            binding.etDate.setOnClickListener {
                val calendar = Calendar.getInstance()
                val year = calendar.get(Calendar.YEAR)
                val month = calendar.get(Calendar.MONTH)
                val day = calendar.get(Calendar.DAY_OF_MONTH)

                val datePickerDialog = DatePickerDialog(
                    requireContext(),
                    { _, year, month, dayOfMonth ->

                        tanggalPergi = "$year-${month + 1}-$dayOfMonth"
                        binding.etDate.setText(tanggalPergi)
                    },
                    year, month, day,
                )
                datePickerDialog.show()
                datePickerDialog.setOnDateSetListener { datePicker, _, _, _ ->
                    val month = datePicker.month
                    val tahunDeparture = datePicker.year
                    val hariDeparture = datePicker.dayOfMonth
                    val tanggalDeparture = "$tahunDeparture-${month + 1}-$hariDeparture"
                    hmVm.saveDepartureDate(tanggalDeparture)
                    findNavController().navigate(R.id.hasilPencarianFragment)
                }
                datePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE)
                    .setTextColor(resources.getColor(R.color.darkblue_05))
                datePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE)
                    .setTextColor(resources.getColor(R.color.darkblue_05))
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun dateToolbarReturn(dateReturn: String?) {
        if (dateReturn != null) {
            binding.etDateReturn.setText(dateReturn)
            binding.etDateReturn.setOnClickListener {
                val calendar = Calendar.getInstance()
                val year = calendar.get(Calendar.YEAR)
                val month = calendar.get(Calendar.MONTH)
                val day = calendar.get(Calendar.DAY_OF_MONTH)

                val datePickerDialog = DatePickerDialog(
                    requireContext(),
                    { _, year, month, dayOfMonth ->

                        val tanggalKembali = "$year-${month + 1}-$dayOfMonth"
                        binding.etDateReturn.setText(tanggalKembali)
                    },
                    year, month, day,
                )
                datePickerDialog.show()
                datePickerDialog.setOnDateSetListener { datePicker, _, _, _ ->
                    val month = datePicker.month
                    val tahunDeparture = datePicker.year
                    val hariDeparture = datePicker.dayOfMonth
                    val tanggalReturn = "$tahunDeparture-${month + 1}-$hariDeparture"
                    binding.etDateReturn.setText(tanggalReturn)
                    hmVm.saveDatePref(tanggalReturn)
                    val fragId = findNavController().currentDestination?.id
                    findNavController().popBackStack(fragId!!, true)
                    findNavController().navigate(fragId)
                }

            }
        }
    }

    private fun returnOnly(
        cityFrom: String?,
        cityTo: String?,
        seatClass: String?,
        dateReturn: String?
    ) {
        val cityFromReturn = cityFrom
        val cityToReturn = cityTo
        hmVm.searchallticket(cityFromReturn!!, cityToReturn!!, seatClass!!, dateReturn!!)
        hmVm.livedatasearchallticket.observe(viewLifecycleOwner) {
            binding.rvListItem.apply {
                val searchAdapter = RoundAdapter(it)
                layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL,false)
                adapter = searchAdapter
            }
        }
    }

}

