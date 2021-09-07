package com.anirudh.medremind.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.anirudh.medremind.data.local.Schedule
import com.anirudh.medremind.data.local.SessionList

@Dao
interface SessionListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSession(sessionList: SessionList)

    @Query("DELETE FROM session_list")
    fun deleteTable()

}