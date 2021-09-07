package com.anirudh.medremind.data

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Relation
import com.anirudh.medremind.data.local.Drug
import com.anirudh.medremind.data.local.Schedule
import com.anirudh.medremind.data.local.SessionList
import com.anirudh.medremind.data.local.Video

//data class ScheduleListModel(
//    val scheduleCd: String,
//    val type: String,
//    val session: String,
//    val foodContext: String,
//    val reminderDate: String,
//) {
//    var brandName: String? = null
//    var dose: Float? = null
//    var unit: String? = null
//    var title: String? = null
//    var hlsUrl: String? = null
//    var thumbnail: String? = null
//    var done: String? = null
//}
//
data class ScheduleListModel(
    @Embedded
    val schedule: Schedule?,
    @Relation(
        parentColumn = "reminder_date",
        entityColumn = "reminder_date"
    )
    val sessionList: SessionList?,
    @Relation(
        parentColumn = "schedule_cd",
        entityColumn = "schedule_cd"
    )
    val drug: Drug?,
    @Relation(
        parentColumn = "schedule_cd",
        entityColumn = "schedule_cd"
    )
    val video: Video?

)