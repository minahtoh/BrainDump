package com.example.braindump.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.braindump.R
import com.example.braindump.databinding.ViewDumpLayoutBinding
import com.example.braindump.model.formattedDate

class ViewDumpFragment: Fragment() {
    private lateinit var binding: ViewDumpLayoutBinding
    private val args: ViewDumpFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ViewDumpLayoutBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupFields()

    }




    private fun setupFields() {
        val dump = args.dump
        binding.apply {
            dumpTextview.text = dump.dump
            val formattedDate = dump.formattedDate()
            dumpDate.text = formattedDate
            feelingsIcon.setImageResource(
                when(dump.feelings) {
                    1 -> { R.drawable.crying_face_96 }
                    2 -> { R.drawable.frowning_face_96}
                    3 -> { R.drawable.slightly_smiling_face_96}
                    4 -> { R.drawable.smiling_face_with_smiling_eyes_96}
                    else -> { R.drawable.grinning_face_with_big_eyes_96}
                }
            )
        }
    }

}