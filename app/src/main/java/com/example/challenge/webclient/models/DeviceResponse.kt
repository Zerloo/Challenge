package com.example.challenge.webclient.models

import com.example.challenge.Device

class DeviceResponse(
    val id: String?,
    val name: String?,
    val type: String?,
    val mac: String?,
    val favorite: String?
) {
    val device: Device
        get() = Device(
            id = "0",
            name = name ?: "",
            type = type ?: "",
            mac = mac ?: "",
            favorite = favorite ?: "",
            )
}