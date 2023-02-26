package com.example.challenge.webclient

import com.example.challenge.Device
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.ResponseBody
import retrofit2.Response

class AlarmWebClient {
    private val token: String = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjZkM2U0YjgzLWY5ZDAtNDFkMy1iMGE5LWE5OTE1ZmM1OTdmNSIsImlhdCI6MTY3NjY1OTQwMCwiZXhwIjoxNjc5MjUxNDAwfQ.U2pa5k9MQvWdqSrERc1RByGIIPW_ihR41-TFilexGoU"

    suspend fun getAlarm(): List<Device> {
        val response = retrofitInitialization(token).alarmDeviceService.alarmDeviceService()
        val devices = mutableListOf<Device>()
        if (response.isSuccessful) {
            val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
            val adapter = moshi.adapter(ResponseData::class.java)
            val responseData = response.body()?.string()?.let { adapter.fromJson(it) }
            responseData?.data?.let { dataArray ->
                for (deviceJson in dataArray) {
                    val device = Device(
                        name = deviceJson.name,
                        type = "Alarm",
                        mac = deviceJson.macAddress,
                        favorite = "True",
                        id = deviceJson.id
                    )
                    devices.add(device)
                }
            }
        }
        return devices
    }

    data class ResponseData(val count: Int, val data: List<DeviceJson>)
    data class DeviceJson(val id: String, val name: String, val macAddress: String, val password: String)
}
