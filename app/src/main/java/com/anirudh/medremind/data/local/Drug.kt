package com.anirudh.medremind.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "drugs")
data class Drug(
    @PrimaryKey()
    @ColumnInfo(name = "schedule_cd")
    var scheduleCd: String,
    @ColumnInfo(name = "brand_name")
    var brandName: String,
    @ColumnInfo(name = "generic_name")
    var genericName: String,
    @ColumnInfo(name = "quantity")
    var quantity: Int,
    @ColumnInfo(name = "dose")
    var dose: Float,
    @ColumnInfo(name = "unit")
    var unit: String,
)