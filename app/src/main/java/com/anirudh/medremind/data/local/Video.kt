package com.anirudh.medremind.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "videos")
data class Video(
    @PrimaryKey()
    @ColumnInfo(name = "schedule_cd")
    var scheduleCd: String,
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "hls_url")
    var hlsUrl: String,
    @ColumnInfo(name = "thumbnail")
    var thumbnail: String,
)