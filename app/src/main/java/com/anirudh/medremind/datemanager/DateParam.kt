package com.anirudh.medremind.datemanager

import java.util.*

class DateParam(_day:String,_month:String) {
    var day: String? = _day
    var month: String? = _month
    var currentTime: Date?=null

    constructor(_day:String,_month:String,_currentTime:Date):this(_day,_month){
        this.day=_day
        this.month=_month
        this.currentTime=_currentTime
    }
}