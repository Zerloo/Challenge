package com.example.challenge.webclient

import com.example.challenge.webclient.models.AlarmDevice
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class AlarmWebClient {
    private val token: String =
        "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjZkM2U0YjgzLWY5ZDAtNDFkMy1iMGE5LWE5OTE1ZmM1OTdmNSIsImlhdCI6MTY3NjY1OTQwMCwiZXhwIjoxNjc5MjUxNDAwfQ.U2pa5k9MQvWdqSrERc1RByGIIPW_ihR41-TFilexGoU"

    suspend fun getAlarm(): List<AlarmDevice> {
        val response = retrofitInitialization(token).alarmDeviceService.alarmDeviceService()
        val devices = mutableListOf<AlarmDevice>()
        if (response.isSuccessful) {
            val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
            val adapter = moshi.adapter(ResponseData::class.java)
            val responseData = response.body()?.string()?.let { adapter.fromJson(it) }
            responseData?.data?.let { dataArray ->
                for (deviceJson in dataArray) {
                    val device = AlarmDevice(
                        id = deviceJson.id,
                        name = deviceJson.name,
                        type = "Alarm",
                        macAddress = deviceJson.macAddress,
                        password = deviceJson.password,
                        favorite = "false",
                    )
                    devices.add(device)
                }
            }
        }
        return devices
    }

    data class ResponseData(val count: Int, val data: List<DeviceJson>)
    data class DeviceJson(
        val id: String,
        val name: String,
        val macAddress: String,
        val password: String
    )
}
