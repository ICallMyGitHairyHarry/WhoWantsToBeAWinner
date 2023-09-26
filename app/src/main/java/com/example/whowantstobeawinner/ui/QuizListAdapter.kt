package com.example.whowantstobeawinner.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.whowantstobeawinner.databinding.QuizItemBinding
import com.example.whowantstobeawinner.model.Quiz

class QuizListAdapter(private val clickListener: QuizItemListener) :
    androidx.recyclerview.widget.ListAdapter<Quiz, QuizListAdapter.QuizItemViewHolder>(DiffCallback) {

    class QuizItemViewHolder(private var binding: QuizItemBinding)
        : RecyclerView.ViewHolder(binding.root){
        fun bind(clickListener: QuizItemListener, quiz: Quiz) {
            binding.quiz = quiz
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Quiz>() {

        override fun areItemsTheSame(oldItem: Quiz, newItem: Quiz): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Quiz, newItem: Quiz): Boolean {
            return oldItem.name == newItem.name
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return QuizItemViewHolder(
            QuizItemBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: QuizItemViewHolder, position: Int) {
        val quiz = getItem(position)
        holder.bind(clickListener, quiz)
    }
}

class QuizItemListener(val clickFunction: (quizId: Int) -> Unit) {
    fun onClick(quizId: Int) = clickFunction(quizId)
}