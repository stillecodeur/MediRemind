package com.anirudh.medremind.ui.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.anirudh.medremind.data.ScheduleListModel
import com.anirudh.medremind.databinding.FragmentMorningBinding
import com.anirudh.medremind.ui.main.home.adapters.ScheduleAdapter
import java.util.*


class NightFragment : Fragment() , OnDateChangeListener {
    lateinit var viewModel: HomeViewModel
    lateinit var scheduleList: MutableList<ScheduleListModel>
    private var adapter: ScheduleAdapter?=null
    private var binding: FragmentMorningBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
        scheduleList = mutableListOf()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMorningBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loadData(Date())
    }

    private fun loadData(date: Date?) {
        scheduleList.clear()

        viewModel.getSchedules(date!!, "NIGHT").observe(requireActivity(), {
            scheduleList.addAll(it)
            if (adapter == null) {
                adapter = ScheduleAdapter(requireContext(), scheduleList)
                binding?.recyclerView?.layoutManager = LinearLayoutManager(requireContext())
                binding?.recyclerView?.adapter = adapter
            } else {
                adapter?.notifyDataSetChanged()
            }
        })


    }



    override fun onChange(date: Date?) {
        loadData(date)
    }

}