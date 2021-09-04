package com.anirudh.medremind

import android.os.Bundle
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.anirudh.medremind.ui.main.SectionsPagerAdapter
import com.anirudh.medremind.databinding.ActivityMainBinding
import com.anirudh.medremind.datemanager.DateManager
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)

        val dm = DateManager()
        val cd=dm.getCurrentDate()
        setDateText(cd.day,cd.month)

        binding.imvNextDate.setOnClickListener {
            val nd = dm.getNextDate()
            setDateText(nd.day, nd.month)
        }

        binding.imvPreviousDate.setOnClickListener {
            val pd = dm.getPreviousDate()
            setDateText(pd.day, pd.month)
        }


    }


    private fun setDateText(day: String?, Month: String?) {
        binding.tvDayText.text = day
        binding.tvMonthText.text = Month
    }
}