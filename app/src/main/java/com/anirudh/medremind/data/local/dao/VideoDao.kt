package com.anirudh.medremind.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.anirudh.medremind.data.local.Drug
import com.anirudh.medremind.data.local.Video

@Dao
interface VideoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVideo(video: Video)

    @Query("SELECT * FROM videos WHERE schedule_cd =:scheduleCd")
    fun getVideo(scheduleCd: String?): Video

    @Query("DELETE FROM videos")
    fun deleteTable()
}