package com.anirudh.medremind.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.anirudh.medremind.data.ScheduleListModel
import com.anirudh.medremind.data.local.Drug
import com.anirudh.medremind.data.local.Schedule
import java.util.*

@Dao
interface ScheduleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSchedule(schedule: Schedule)

    @Query("SELECT * FROM schedule_list WHERE reminder_date =:reminderDate")
    fun getSchedules(reminderDate: String): List<Schedule>

    @Query("DELETE FROM schedule_list")
    fun deleteTable()


}