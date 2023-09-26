package com.example.whowantstobeawinner

import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.whowantstobeawinner.model.Quiz
import com.example.whowantstobeawinner.ui.QuizAppApiStatus
import com.example.whowantstobeawinner.ui.QuizListAdapter

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Quiz>?) {
    val adapter = recyclerView.adapter as QuizListAdapter
    adapter.submitList(data)
}

@BindingAdapter("quizApiStatus")
fun bindStatus(linearLayout: LinearLayout, status: QuizAppApiStatus?) {

    val progressBar = linearLayout.findViewById<ProgressBar>(R.id.progress_bar)
    val errorMessage = linearLayout.findViewById<TextView>(R.id.error_message)
    val retryButton = linearLayout.findViewById<Button>(R.id.retry_button)
    val mainLayout = linearLayout.findViewById<LinearLayout>(R.id.main_layout)

    when (status) {
        QuizAppApiStatus.LOADING -> {
            progressBar.visibility = View.VISIBLE
            errorMessage.visibility = View.GONE
            retryButton.visibility = View.GONE
            mainLayout.visibility = View.GONE
        }
        QuizAppApiStatus.ERROR -> {
            progressBar.visibility = View.GONE
            errorMessage.visibility = View.VISIBLE
            retryButton.visibility = View.VISIBLE
            mainLayout.visibility = View.GONE
        }
        QuizAppApiStatus.DONE -> {
            progressBar.visibility = View.GONE
            errorMessage.visibility = View.GONE
            retryButton.visibility = View.GONE
            mainLayout.visibility = View.VISIBLE
        }
        null -> {}
    }
}

@BindingAdapter("arrayData")
fun bindListView(listView: ListView,data: Array<String>?) {
    data?.let {
        listView.adapter = ArrayAdapter(
            listView.context, R.layout.quiz_option_item, R.id.quiz_option_view, data
        )
    }
}