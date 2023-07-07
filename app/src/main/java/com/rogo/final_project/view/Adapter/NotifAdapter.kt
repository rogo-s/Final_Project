package com.rogo.final_project.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rogo.final_project.databinding.ItemHasilPencarianBinding
import com.rogo.final_project.databinding.NotificationItemBinding
import com.rogo.final_project.view.Adapter.HasilPencarianAdapter
import com.rogo.final_project.view.Adapter.HomeAdapter
import com.rogo.final_project.view.model.data.notifikasi.Data
import com.rogo.final_project.view.model.data.notifikasi.ResponseGetNotif


class NotifAdapter(private var listNotif : List<Data>) : RecyclerView.Adapter<NotifAdapter.ViewHolder>() {

    fun setDataNotif(notif : List<Data>){
        this.listNotif = notif
    }
    class ViewHolder(val binding : NotificationItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = NotificationItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotifAdapter.ViewHolder, position: Int) {
        holder.binding.Tittle.text = listNotif[position].title
        holder.binding.DetailNotif.text = listNotif[position].text
        holder.binding.tgl.text = listNotif[position].createdAt

    }

    override fun getItemCount(): Int {
        return listNotif.size
    }
}