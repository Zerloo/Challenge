package com.example.challenge.webclient.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity
data class VideoDevice(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val serial: String,
    val username: String,
    val password: String,
    val type: String,
    val favorite: String,
)