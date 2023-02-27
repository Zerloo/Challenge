package com.example.challenge

import android.bluetooth.BluetoothClass.Device
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.RecyclerView
import com.example.challenge.dao.AppDataBase
import com.example.challenge.dao.VideoDeviceDao
import com.example.challenge.webclient.VideoWebClient
import com.example.challenge.webclient.models.VideoDevice
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var deviceList: List<VideoDevice>
    private lateinit var lifecycleScope: LifecycleCoroutineScope

    private val webClientVideo by lazy {
        VideoWebClient()
    }
    private val daoVideo by lazy {
        AppDataBase.instancia(this).videoDeviceDao()
    }

    private val daoAlarm by lazy {
        AppDataBase.instancia(this).alarmDeviceDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope = lifecycle.coroutineScope

        lifecycleScope.launch {
            deviceList = webClientVideo.getVideo()
            configRecyclerView()
        }
    }

    private fun configRecyclerView(): RecyclerView {
        val deviceListRecyclerView: RecyclerView = findViewById(R.id.deviceListRecyclerView)
        deviceListRecyclerView.adapter = DeviceListAdapter(deviceList)
        configBottomNavigationView(deviceListRecyclerView)
        return deviceListRecyclerView
    }

    private fun configBottomNavigationView(deviceListRecyclerView: RecyclerView) {
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
                R.id.alarmDevicesMenuItem -> {
                    val alarmDevices = deviceList.filter { it.type == "Alarm" }
                    deviceListRecyclerView.adapter = DeviceListAdapter(alarmDevices)
                    true
                }
                R.id.favoriteDevicesMenuItem -> {
                    val favoriteDevices = deviceList.filter { it.favorite == "true" }
                    deviceListRecyclerView.adapter = DeviceListAdapter(favoriteDevices)
                    true
                }
                else -> false
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.kebab_menu, menu)
        return true
    }

/// Criando Funções Dao
    private fun saveVideoDevice() {
        val device = createDevice()
        lifecycleScope.launch() {
            daoVideo.saveVideoDevice(device)
            finish()
        }
    }

}

