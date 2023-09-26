package com.example.whowantstobeawinner.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.whowantstobeawinner.R
import com.example.whowantstobeawinner.databinding.FragmentQuizResultsBinding

class QuizResultsFragment : Fragment() {

    private val viewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentQuizResultsBinding.inflate(inflater)

        binding.lifecycleOwner = this
        
        binding.viewModel = viewModel

        binding.returnToMenuButton.setOnClickListener {
            findNavController().navigate(R.id.action_quizResultsFragment_to_mainFragment)
        }

        // Inflate the layout for this fragment
        return binding.root

    }

}