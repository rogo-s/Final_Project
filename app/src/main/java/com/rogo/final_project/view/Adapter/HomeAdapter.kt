package com.rogo.final_project.view.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rogo.final_project.databinding.DestinasiItemBinding
import com.rogo.final_project.view.model.data.DestinasiItem

class HomeAdapter(private val listItem : List<DestinasiItem>):RecyclerView.Adapter<HomeAdapter.ItemViewHolder>() {
    class ItemViewHolder(private val binding : DestinasiItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item:DestinasiItem){
            binding.ivDestinasi.setImageResource(item.img)
            binding.tvDestinasi.text = item.jenisDestinasi
            binding.tipePesawat.text = item.tipePesawat
            binding.tvTanggal.text = item.tgl
            binding.tvMulaiDari.text = item.harga
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DestinasiItemBinding.inflate(inflater,parent,false)
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return listItem.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
       val item = listItem[position]
        holder.bind(item)
    }
}