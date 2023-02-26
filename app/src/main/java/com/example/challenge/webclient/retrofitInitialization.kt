package com.example.challenge.webclient

import com.example.challenge.webclient.services.AlarmDeviceService
import com.example.challenge.webclient.services.VideoDeviceService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class retrofitInitialization(token: String) {
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain: Interceptor.Chain ->
            val request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
            chain.proceed(request)
        }
        .build()

    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("http://squad-apps.ddns.net:3000/")
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val alarmDeviceService: AlarmDeviceService = retrofit.create(AlarmDeviceService::class.java)
    val videoDeviceService: VideoDeviceService = retrofit.create(VideoDeviceService::class.java)
}
