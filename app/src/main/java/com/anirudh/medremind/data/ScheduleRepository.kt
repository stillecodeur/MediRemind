package com.anirudh.medremind.data

import android.util.Log
import androidx.lifecycle.LiveData
import com.anirudh.medremind.data.local.*
import com.anirudh.medremind.data.remote.RetrofitApiService
import com.anirudh.medremind.data.remote.model.ScheduleModel
import com.anirudh.medremind.datemanager.DateManager
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*


class ScheduleRepository(
    private val retrofitApiService: RetrofitApiService,
    private val appDatabase: AppDatabase
) {

    init {
        if (appDatabase.scheduleDao().getRows() == 0) {
            GlobalScope.launch {
                val scheduleList = retrofitApiService.getScheduleList().body()?.scheduleList
                saveSchedulesToDb(scheduleList)
            }
        }
    }


    fun markScheduleDone(schedule: Schedule) {
        appDatabase.scheduleDao()
            .markDone(scId = schedule.scheduleCd, schedule.reminderDate, schedule.session)
    }

    fun getSchedules(date: Date, session: String): LiveData<List<ScheduleListModel>> {
        return appDatabase.scheduleListModel()
            .getSchedulesList(DateManager().getDateText2(date), session)
    }


    suspend fun saveSchedulesToDb(remoteSchedules: List<ScheduleModel>?) {
        remoteSchedules?.forEach {
            val dm = DateManager()
            val reminderDates = dm.getReminderDates(it.frequency, it.duration)
            val scheduleCd = it.scheduleCd
            val type = it.type
            val sessions = it.sessionList

            reminderDates.forEach {
                val dateText = dm.getDateText2(it)
                sessions.forEach {
                    val schedule =
                        Schedule(
                            scheduleCd,
                            type,
                            it.session,
                            it.foodContext,
                            dateText
                        )
                    appDatabase.scheduleDao().insertSchedule(schedule)
                }

            }


            if (it.drug != null) {
                val drug = Drug(
                    it.scheduleCd, it.drug.brandNm, it.drug
                        .genericNm, it.drug.qty, it.drug.dosage.dose.toFloat(), it.drug.dosage.unit
                )
                appDatabase.drugDao().insertDrug(drug)
            }

            if (it.video != null) {
                val video =
                    Video(it.scheduleCd, it.video.title, it.video.hlsUrl, it.video.thumbnail)
                appDatabase.videoDao().insertVideo(video)
            }

        }
    }


}