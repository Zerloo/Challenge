package com.example.challenge

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DeviceListAdapter(private val deviceList: List<Device>) :
    RecyclerView.Adapter<DeviceListAdapter.DeviceViewHolder>() {

    class DeviceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.deviceNameTextView)
        val typeTextView: TextView = itemView.findViewById(R.id.deviceTypeTextView)
        val descriptionTextView: TextView = itemView.findViewById(R.id.deviceDescriptionTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.device_list_item, parent, false)
        return DeviceViewHolder(view)
    }

    override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) {
        val device = deviceList[position]
        holder.nameTextView.text = device.name
        holder.typeTextView.text = device.type
        holder.descriptionTextView.text = device.description
    }

    override fun getItemCount(): Int = deviceList.size
}

