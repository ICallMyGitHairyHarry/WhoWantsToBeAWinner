package com.example.whowantstobeawinner.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.whowantstobeawinner.R
import com.example.whowantstobeawinner.databinding.FragmentMainBinding
import com.example.whowantstobeawinner.model.Quiz

class MainFragment : Fragment() {

    private val viewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentMainBinding.inflate(inflater)

        viewModel.getQuizesList()

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        binding.quizRecyclerView.adapter = QuizListAdapter(QuizItemListener { quizId ->
            // viewModel.onQuizClicked(quizId)
             findNavController()
                 .navigate(R.id.action_mainFragment_to_quizQuestionFragment)
            Toast.makeText(context, quizId.toString(), Toast.LENGTH_SHORT).show()
        })

        binding.retryButton.setOnClickListener { viewModel.getQuizesList() }
        
        // Inflate the layout for this fragment
        return binding.root

    }

}