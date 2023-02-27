package com.example.challenge.dao

import android.content.Context
import android.provider.MediaStore.Video
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.challenge.webclient.models.AlarmDevice
import com.example.challenge.webclient.models.VideoDevice

@Database(
    version = 1,
    entities = [VideoDevice::class, AlarmDevice::class]
)

abstract class AppDataBase : RoomDatabase() {
    abstract fun videoDeviceDao() : VideoDeviceDao
    abstract fun alarmDeviceDao() : AlarmDeviceDao


    companion object {
        @Volatile
        private var db: AppDataBase? = null

        fun instancia(context: Context): AppDataBase {
            return db ?: Room.databaseBuilder(
                context,
                AppDataBase::class.java,
                "challenge.db"
            ).build()
        }
    }
}