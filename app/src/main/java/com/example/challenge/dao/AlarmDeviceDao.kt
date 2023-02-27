package com.example.challenge.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.example.challenge.webclient.models.AlarmDevice
import kotlinx.coroutines.flow.Flow


@Dao
interface AlarmDeviceDao {

    @Insert (onConflict = REPLACE)
    suspend fun saveAlarmDevice (videoDevice: List<AlarmDevice>)

    @Query("SELECT * FROM VideoDevice")
    fun getAlarmDevice () : Flow<List<AlarmDevice>>

    @Query("DELETE FROM VideoDevice WHERE id = :id")
    suspend fun deleteAlarmDevice(id: String)

}