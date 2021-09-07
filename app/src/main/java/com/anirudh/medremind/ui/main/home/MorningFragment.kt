package com.anirudh.medremind.ui.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.anirudh.medremind.R
import com.anirudh.medremind.data.ScheduleListModel
import com.anirudh.medremind.databinding.FragmentMorningBinding
import com.anirudh.medremind.ui.main.home.adapters.ScheduleAdapter
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MorningFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MorningFragment : Fragment(), OnDateChangeListener {

    lateinit var viewModel: HomeViewModel
    lateinit var scheduleList: MutableList<ScheduleListModel>
    private var adapter: ScheduleAdapter? = null
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
        setAdapter()
        setClickEvents()
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loadData(Date())
    }

    private fun loadData(date: Date?) {

        viewModel.getSchedules(date!!, "MORNING").observe(requireActivity(), {
            scheduleList.clear()
            scheduleList.addAll(it)
            if (adapter == null) {
               setAdapter()
            } else {
                adapter?.notifyDataSetChanged()
            }
        })


    }

    private fun setAdapter() {
        adapter = ScheduleAdapter(requireContext(), scheduleList)
        binding?.recyclerView?.layoutManager = LinearLayoutManager(requireContext())
        binding?.recyclerView?.adapter = adapter
    }

    private fun setClickEvents() {
        adapter?.onDoneClickListener = object : ScheduleAdapter.OnDoneClickListener {
            override fun onDone(scheduleListModel: ScheduleListModel) {
                viewModel.updateDoneStatus(scheduleListModel)
            }

        }
    }

    override fun onChange(date: Date?) {
        loadData(date)
    }

}