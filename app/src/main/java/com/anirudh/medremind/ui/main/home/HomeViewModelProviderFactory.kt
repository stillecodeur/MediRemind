package com.anirudh.medremind.ui.main.home

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anirudh.medremind.data.ScheduleRepository
import com.anirudh.medremind.data.local.AppDatabase
import com.anirudh.medremind.data.remote.RetrofitApiService

class HomeViewModelProviderFactory(val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val appDatabase = AppDatabase.getInstance(application)
        val apiService = RetrofitApiService.getInstance()
        val repository = ScheduleRepository(apiService, appDatabase)
        val viewModel = HomeViewModel(repository)
        return viewModel as T
    }

}