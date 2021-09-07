package com.anirudh.medremind.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.anirudh.medremind.data.local.Drug

@Dao
interface DrugDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDrug(drug: Drug)

    @Query("SELECT * FROM drugs WHERE schedule_cd =:scheduleCd")
    fun getDrug(scheduleCd: String?) : Drug

    @Query("DELETE FROM drugs")
    fun deleteTable()
}