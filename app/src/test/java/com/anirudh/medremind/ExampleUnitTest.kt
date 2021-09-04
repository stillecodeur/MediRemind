package com.anirudh.medremind

import com.anirudh.medremind.datemanager.DateParam
import org.junit.Test

import org.junit.Assert.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val ss=getDateText(Date())
        assertEquals(ss,"04-Sep" )
    }


    fun getDateText(d: Date): String {
        var calendar=Calendar.getInstance()
        calendar.time = d
        var dateFormat = SimpleDateFormat("dd-MMM-yyyy");
        var dd = dateFormat.format(calendar.time)
        val arr = dd.split("-")
        return arr[0]+"-"+arr[1]
    }
}