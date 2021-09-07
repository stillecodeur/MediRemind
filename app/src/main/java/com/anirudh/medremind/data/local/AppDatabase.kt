package com.anirudh.medremind.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.anirudh.medremind.data.ScheduleListModelDao
import com.anirudh.medremind.data.local.converters.DateConverter
import com.anirudh.medremind.data.local.converters.SessionListConverter
import com.anirudh.medremind.data.local.dao.DrugDao
import com.anirudh.medremind.data.local.dao.ScheduleDao
import com.anirudh.medremind.data.local.dao.VideoDao

@Database(
    entities = arrayOf(Schedule::class, SessionList::class, Drug::class, Video::class),
    version = 1,
    exportSchema = false
)
@TypeConverters(DateConverter::class, SessionListConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun drugDao(): DrugDao
    abstract fun videoDao(): VideoDao
    abstract fun scheduleDao(): ScheduleDao
    abstract fun scheduleListModel(): ScheduleListModelDao

    companion object {

        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {

            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {

                INSTANCE = Room
                    .databaseBuilder(context, AppDatabase::class.java, "app_database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()

                return INSTANCE!!
            }
        }


    }
}