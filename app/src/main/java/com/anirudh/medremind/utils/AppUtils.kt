package com.anirudh.medremind.utils

import java.text.SimpleDateFormat
import java.util.*

object AppUtils {

    fun isPastDate(date: String):Boolean {
      return  SimpleDateFormat(ConstantUtils.DATE_FORMAT).parse(date).before(Date())
    }
}