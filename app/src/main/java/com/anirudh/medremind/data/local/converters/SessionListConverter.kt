package com.anirudh.medremind.data.local.converters

import androidx.room.TypeConverter
import com.anirudh.medremind.data.remote.model.SessionModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class SessionListConverter {
    @TypeConverter
    fun listToJson(value: List<SessionModel>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<SessionModel>::class.java).toList()
}
