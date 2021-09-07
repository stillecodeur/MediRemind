package com.anirudh.medremind.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface ScheduleListModelDao {
    @Query("SELECT * FROM schedule_list WHERE reminder_date=:date AND session=:session")
//@Query("SELECT * FROM schedule_list as sl JOIN session_list as ss WHERE sl.reminder_date=:date AND ss.reminder_date=:date AND ss.session=:session" )
    @Transaction
    fun getSchedulesList(date: String, session: String): LiveData<List<ScheduleListModel>>

    @Query("DELETE FROM schedule_list")
    fun deleteTable()

}