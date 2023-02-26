package com.example.challenge.webclient.services

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface AlarmDeviceService {

    @GET("alarm-centrals")
    suspend fun alarmDeviceService() : Response<ResponseBody>

}

interface VideoDeviceService {

    @GET ("video-centrals")
    suspend fun videoDeviceService() : Response<ResponseBody>

}