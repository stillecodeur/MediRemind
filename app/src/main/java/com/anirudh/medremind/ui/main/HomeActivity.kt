package com.anirudh.medremind.ui.main

import android.os.Bundle
import android.widget.Toast
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.anirudh.medremind.R
import com.anirudh.medremind.databinding.ActivityMainBinding
import com.anirudh.medremind.datemanager.DateManager
import com.anirudh.medremind.ui.main.home.HomeViewModel
import com.anirudh.medremind.ui.main.home.HomeViewModelProviderFactory
import com.anirudh.medremind.ui.main.home.OnDateChangeListener
import com.anirudh.medremind.utils.AppUtils
import java.util.*

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var onDateListener: MutableList<OnDateChangeListener>

    val viewModel: HomeViewModel by lazy {
        val viewModelProviderFactory =
            HomeViewModelProviderFactory(
                application
            )
        ViewModelProvider(
            this,
            viewModelProviderFactory
        )[HomeViewModel::class.java]
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        viewPager.offscreenPageLimit = 4
        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)

        val dm = DateManager()
        val cd = dm.getCurrentDate()
        setDateText(cd.day, cd.month)

        onDateListener = mutableListOf()

        binding.imvNextDate.setOnClickListener {
            val nd = dm.getNextDate()
            setDateText(nd.day, nd.month)

            onDateListener.forEach {
                it.onChange(nd.currentTime)
            }
        }

        binding.imvPreviousDate.setOnClickListener {
            val pd = dm.getPreviousDate()
            val dtText = dm.getDateText2(pd.currentTime!!)
            if (AppUtils.isPastDate(dtText)) {
                Toast.makeText(
                    this@HomeActivity,
                    "Past date",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                setDateText(pd.day, pd.month)
                onDateListener.forEach {
                    it.onChange(pd.currentTime)
                }
            }
        }

        createSchedules(Date())
    }


    private fun setDateText(day: String?, Month: String?) {
        binding.tvDayText.text = day
        binding.tvMonthText.text = Month
    }

    private fun createSchedules(date: Date) {
        val hh = viewModel.getSchedules(date, getString(R.string.morning))
    }

    override fun onAttachFragment(fragment: Fragment) {
        if (fragment is OnDateChangeListener) {
            onDateListener.add(fragment)
        }

    }

}