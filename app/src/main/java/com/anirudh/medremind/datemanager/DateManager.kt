package com.anirudh.medremind.datemanager

import java.text.SimpleDateFormat
import java.util.*

class DateManager() {
    private lateinit var calendar: Calendar
    private lateinit var current_time: Date

    init {
        calendar = Calendar.getInstance()
        current_time = Date()
        calendar.time = current_time
    }

    fun getCurrentDate(): DateParam {
        current_time = Date()
        calendar.time=current_time
        return getDateText(current_time)
    }

    fun getNextDate(): DateParam {
        calendar.add(Calendar.DATE, 1)
        current_time = calendar.time
        return getDateText(calendar.time)
    }

    fun getPreviousDate(): DateParam {
        calendar.add(Calendar.DATE, -1)
        current_time = calendar.time
        return getDateText(calendar.time)
    }


    fun getDateText(d: Date): DateParam {
        calendar.time = d
        var dateFormat = SimpleDateFormat("dd-MMM-yyyy");
        var dd = dateFormat.format(calendar.time)
        val arr = dd.split("-")
        return DateParam(arr[0], arr[1])
    }
}