package com.anirudh.medremind.data.remote.model

import com.anirudh.medremind.data.local.Drug

data class ScheduleModel(
    val scheduleCd: String,
    val type: String,
    val frequency: Int,
    val duration: Int,
    val sessionList: List<SessionModel>,
    val drug: DrugModel? = null,
    val video: VideoModel? = null
)