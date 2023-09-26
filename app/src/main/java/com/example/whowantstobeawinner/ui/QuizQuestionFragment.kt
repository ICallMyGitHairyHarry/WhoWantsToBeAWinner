package com.example.whowantstobeawinner.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.whowantstobeawinner.R
import com.example.whowantstobeawinner.databinding.FragmentQuizQuestionBinding

class QuizQuestionFragment : Fragment() {

    private val viewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentQuizQuestionBinding.inflate(inflater)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        viewModel.getQuestion(1)

        binding.optionsListView.onItemClickListener =
            OnItemClickListener { parent, _, position, _ ->
                findNavController().navigate(R.id.action_quizQuestionFragment_to_quizResultsFragment)
                Toast.makeText(
                    requireContext(), parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT
                ).show()
            }

        // Inflate the layout for this fragment
        return binding.root
    }

}