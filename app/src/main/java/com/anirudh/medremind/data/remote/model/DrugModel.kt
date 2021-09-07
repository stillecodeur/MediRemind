package com.anirudh.medremind.data.remote.model

data class DrugModel(

    val brandNm: String,
    val genericNm: String,
    val qty: Int,
    val dosage: DosageModel
)