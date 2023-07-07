package com.rogo.final_project.view.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rogo.final_project.databinding.ItemHistoryBinding
import com.rogo.final_project.view.model.data.search.Data
import com.rogo.final_project.view.model.data.search.Flight

class RiwayatAdapter(val listItem : List<Flight>) : RecyclerView.Adapter<RiwayatAdapter.ViewHolder>() {

    class ViewHolder(private val binding : ItemHistoryBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item : Flight){
            binding.apply {
                tvAsalPenerbangan.text = item.departureCity
                tvTanggalBerangkat.text = item.departureDate
                tvJamBerangkat.text = item.departureTime
                tvDurasiPenerbangan.text = item.duration
                tvTujuanPenerbangan.text = item.arrivalCity
                tvTanggalDatang.text = item.arrivalDate
                tvJamDatang.text = item.arrivalTime
                tvBookCode.text = item.flightCode
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHistoryBinding.inflate(inflater,parent,false)
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listItem[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return listItem.size
    }
}