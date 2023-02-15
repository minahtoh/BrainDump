package com.example.braindump.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.braindump.R
import com.example.braindump.databinding.NewDumpLayoutBinding
import com.example.braindump.model.Dump
import com.example.braindump.viewmodel.DumpsViewmodel

class AddDumpFragment:Fragment() {
    private lateinit var binding: NewDumpLayoutBinding
    private val viewModel: DumpsViewmodel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = NewDumpLayoutBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.dumpButton.setOnClickListener{
        addNewDump()
        findNavController().navigate(R.id.action_addDumpFragment_to_homeFragment)
        }
        val rating = binding.starRating
        rating.setOnClickListener {
            rating.rating = (rating.rating +1) % 5
        }
    }

    private fun addNewDump() {
        val dumpText = binding.dumpInput.text
        val dumpTime = System.currentTimeMillis()
        var feelings = 0
        if (binding.saddest.isChecked) feelings = 1
        else if (binding.sad.isChecked) feelings = 2
        else if (binding.normal.isChecked) feelings = 3
        else if (binding.happy.isChecked) feelings = 4
        else if (binding.happiest.isChecked) feelings = 5

        val newDump = Dump(feelings = feelings,
           dump = dumpText.toString(), date = dumpTime)
        viewModel.saveDump(newDump)
    }


}