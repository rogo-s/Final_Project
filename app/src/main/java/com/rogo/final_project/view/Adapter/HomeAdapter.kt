package com.rogo.final_project.view.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rogo.final_project.R
import com.rogo.final_project.databinding.DestinasiItemBinding
import com.rogo.final_project.view.model.data.flight.Flight

class HomeAdapter() : RecyclerView.Adapter<HomeAdapter.ItemViewHolder>() {

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Flight>() {

        override fun areItemsTheSame(oldItem: Flight, newItem: Flight): Boolean {
            return (oldItem == newItem)
        }

        override fun areContentsTheSame(oldItem: Flight, newItem: Flight): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, DIFF_CALLBACK)
    class ItemViewHolder(private val binding: DestinasiItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Flight) {
            binding.ivDestinasi.setImageResource(R.drawable.logo)
            binding.tvDestinasi.text = item.arrivalCity
            binding.tipePesawat.text = item.airline
            binding.tvTanggal.text = item.departureDate
            binding.tvMulaiDari.text = item.flightCode
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DestinasiItemBinding.inflate(inflater, parent, false)
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.bind(item)
    }
}