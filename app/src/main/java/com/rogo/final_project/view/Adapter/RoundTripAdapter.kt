package com.rogo.final_project.view.Adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.rogo.final_project.R
import com.rogo.final_project.databinding.ItemHasilPencarianBinding
import com.rogo.final_project.view.model.data.search.Data

class RoundTripAdapter(val listairport : List<Data>) : RecyclerView.Adapter<RoundTripAdapter.ViewHolder>() {
    class ViewHolder(val binding : ItemHasilPencarianBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemHasilPencarianBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvJamBerangkat.text = listairport[position].flight.departureTime
        holder.binding.tvInisial.text = listairport[position].flight.departureCity
        holder.binding.tvEstimasiWaktu.text = listairport[position].flight.duration
        holder.binding.tvJamTiba.text = listairport[position].flight.arrivalTime
        holder.binding.tvInisialDua.text = listairport[position].flight.arrivalCity
        holder.binding.tvHarga.text = listairport[position].price.toString()
        holder.binding.tvClassSeat.text = listairport[position].classSeat
        holder.binding.tvClassPesawat.text = listairport[position].flight.airline

        holder.binding.cvHasilPencarian.setOnClickListener {item ->
            val id = listairport[position].id
            val bundle = Bundle()
            bundle.putInt("id", id!!)
            Navigation.findNavController(item).navigate(R.id.action_hasilPencarianRoundFragment_to_hasilPencarianReturnFragment, bundle)
        }

    }

    override fun getItemCount(): Int {
        return listairport.size
    }
}