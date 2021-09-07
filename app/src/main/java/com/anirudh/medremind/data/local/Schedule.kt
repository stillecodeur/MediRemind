package com.anirudh.medremind.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "schedule_list")
data class Schedule(
    @ColumnInfo(name = "schedule_cd")
    var scheduleCd: String,
    @ColumnInfo(name = "type")
    var type: String,
    @ColumnInfo(name = "reminder_date")
    var reminderDate: String,
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var Id: Int? = null


}