package com.example.braindump.fragments

import android.graphics.Color
import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidplot.xy.BoundaryMode
import com.androidplot.xy.LineAndPointFormatter
import com.androidplot.xy.SimpleXYSeries
import com.androidplot.xy.StepMode
import com.example.braindump.R
import com.example.braindump.adapters.DumpsRecycler
import com.example.braindump.databinding.HomeLayoutBinding
import com.example.braindump.model.Dump
import com.example.braindump.model.Series
import com.example.braindump.viewmodel.DumpsViewmodel
import java.util.*

class HomeFragment: Fragment() {
    private lateinit var binding: HomeLayoutBinding
    private val viewModel : DumpsViewmodel by activityViewModels()
    private lateinit var recyclerView:DumpsRecycler



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HomeLayoutBinding.inflate(inflater,container,false)
        showGreetingText()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = DumpsRecycler()

        setupRecyclerView()

        setupFeelingsChart()

        recyclerView.setOnItemClickListener { viewDump(it) }

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_addDumpFragment)
        }

    }

    private fun showGreetingText() {
        val calendar = Calendar.getInstance()
        val currentHour = calendar.get(Calendar.HOUR_OF_DAY)

        binding.greetingText.setText(
            when (currentHour) {
                in 6..11 -> { R.string.morning_6to12 }
                in 12..16 -> { R.string.afternoon_12to5 }
                in 17..19 -> { R.string.evening_5to8 }
                in 20..23 -> { R.string.night_8to12 }
                in 0..2 -> { R.string.midnight_12to3 }
                in 3..5 -> { R.string.midnight_3to6 }
                else -> {
                    R.string.midnight_12to3
                }
            }
        )
    }


    private fun viewDump(dump: Dump){
        val action = HomeFragmentDirections.actionHomeFragmentToViewDumpFragment(dump)
            findNavController().navigate(action)
    }

    private fun setupFeelingsChart() {
        val chart = binding.feelsChart
        val listA = viewModel.feelingsHistory()
        listA.observe(viewLifecycleOwner){ feelingsList->
        chart.apply {
            val seriesY = SimpleXYSeries(feelingsList.takeLast(15),SimpleXYSeries.ArrayFormat.Y_VALS_ONLY,
                "")
            //val seriesY = Series(feelingsList)
            val format = LineAndPointFormatter(Color.MAGENTA,
                Color.alpha(R.color.md_theme_dark_onPrimaryContainer),
                R.color.md_theme_dark_onPrimaryContainer,
                null)
            addSeries(seriesY,format)
            setRangeBoundaries(0,6,BoundaryMode.FIXED)
            setRangeStep(StepMode.INCREMENT_BY_VAL,1.0)
            setDomainStep(StepMode.INCREMENT_BY_VAL,5.0)
            graph.gridBackgroundPaint.color = Color.CYAN
            graph.backgroundPaint.color = Color.TRANSPARENT
            }
        }
    }

    private fun setupRecyclerView() {
        binding.mainRecyclerView.apply {
            adapter = recyclerView
            layoutManager = LinearLayoutManager(activity)
        }
        viewModel.displayDumpList().observe(viewLifecycleOwner){
            recyclerView.submitList(it)
        }
    }
}