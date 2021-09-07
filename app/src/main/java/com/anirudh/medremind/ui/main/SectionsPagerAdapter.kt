package com.anirudh.medremind.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.anirudh.medremind.R
import com.anirudh.medremind.ui.main.home.AfternoonFragment
import com.anirudh.medremind.ui.main.home.EveningFragment
import com.anirudh.medremind.ui.main.home.MorningFragment
import com.anirudh.medremind.ui.main.home.NightFragment

private val TAB_TITLES = arrayOf(
    R.string.morning,
    R.string.afternoon,
    R.string.evening,
    R.string.night
)

private val TAB_FRAGMENTS= arrayOf(
    MorningFragment(),
    AfternoonFragment(),
    EveningFragment(),
    NightFragment()
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return TAB_FRAGMENTS[position] as Fragment
    }


    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return 4
    }
}