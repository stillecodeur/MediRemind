package com.anirudh.medremind.data.remote

import com.anirudh.medremind.data.remote.model.ScheduleResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitApiService {
    @GET("dev/remind/schedule")
    suspend fun getScheduleList(): Response<ScheduleResponse>


    companion object {
        private const val BASE_URL = "https://38rhabtq01.execute-api.ap-south-1.amazonaws.com"
        private var serviceRetrofit: RetrofitApiService? = null
        fun getInstance(): RetrofitApiService {
            if (serviceRetrofit == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                serviceRetrofit = retrofit.create(RetrofitApiService::class.java)
            }
            return serviceRetrofit!!
        }

    }
}