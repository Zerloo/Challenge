package com.example.challenge.webclient.models

data class AlarmDevice(
    val id: String,
    val name: String,
    val macAddress: String,
    val password: String,
    val type: String,
    val favorite: String,
)