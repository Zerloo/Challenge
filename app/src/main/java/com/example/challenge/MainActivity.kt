package com.example.challenge

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var deviceList: List<Device>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        deviceList = listOf(
            Device("Device 1", "Audio", "Description of Device 1"),
            Device("Device 2", "Video", "Description of Device 2"),
            Device("Device 3", "Audio", "Description of Device 3"),
            Device("Device 4", "Video", "Description of Device 4")
        )

        val deviceListRecyclerView: RecyclerView = findViewById(R.id.deviceListRecyclerView)
        deviceListRecyclerView.adapter = DeviceListAdapter(deviceList)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.homeMenuItem -> {
                    deviceListRecyclerView.adapter = DeviceListAdapter(deviceList)
                    true
                }
                R.id.videoDevicesMenuItem -> {
                    val videoDevices = deviceList.filter { it.type == "Video" }
                    deviceListRecyclerView.adapter = DeviceListAdapter(videoDevices)
                    true
                }
                R.id.audioDevicesMenuItem -> {
                    val audioDevices = deviceList.filter { it.type == "Audio" }
                    deviceListRecyclerView.adapter = DeviceListAdapter(audioDevices)
                    true
                }
                else -> false
            }
        }

    }
}

