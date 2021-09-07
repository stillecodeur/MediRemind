package com.anirudh.medremind.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "session_list")
data class SessionList(
    @ColumnInfo(name = "schedule_cd")
    var scheduleCd: String,
    @ColumnInfo(name = "session")
    var session: String,
    @ColumnInfo(name = "foodContext")
    var foodContext: String,
    @ColumnInfo(name = "reminder_date")
    var reminderDate: String,
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var Id: Int? = null

    @ColumnInfo(name = "done")
    var done: Boolean = false

}