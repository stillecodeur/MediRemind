package com.anirudh.medremind.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.anirudh.medremind.data.ScheduleListModel
import com.anirudh.medremind.data.ScheduleRepository
import java.util.*

class HomeViewModel(private val scheduleRepository: ScheduleRepository) :
    ViewModel() {


    fun getSchedules(date: Date,session:String): LiveData<List<ScheduleListModel>> {
        return scheduleRepository.getSchedules(date,session)
    }
}