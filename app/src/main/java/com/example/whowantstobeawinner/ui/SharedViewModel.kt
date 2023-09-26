package com.example.whowantstobeawinner.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whowantstobeawinner.model.Quiz
import com.example.whowantstobeawinner.model.QuizQuestion
import com.example.whowantstobeawinner.network.QuizAppApi
import kotlinx.coroutines.launch

enum class QuizAppApiStatus {LOADING, ERROR, DONE}

class SharedViewModel: ViewModel() {

    private var _status = MutableLiveData<QuizAppApiStatus>()
    val status : LiveData<QuizAppApiStatus> = _status

    private var _quizes = MutableLiveData<List<Quiz>>()
    val quizes : LiveData<List<Quiz>> = _quizes

    private var _quizQuestions = MutableLiveData<List<QuizQuestion>>()
    val quizQuestions : LiveData<List<QuizQuestion>> = _quizQuestions

    private var _currentQuizQuestion = MutableLiveData<QuizQuestion>()
    val currentQuizQuestion : LiveData<QuizQuestion> = _currentQuizQuestion

    private var _errorMessage = MutableLiveData<String>()
    val errorMessage : LiveData<String> = _errorMessage

    fun getQuizesList() {
        viewModelScope.launch {
            _status.value = QuizAppApiStatus.LOADING
            try {
                val listResult = QuizAppApi.retrofitService.getQuizes()
                // _quizes.value = listResult
                _quizes.value = listOf(
                    Quiz(1, "Животные", listOf(1, 2, 3)),
                    Quiz(2, "Растения", listOf(5, 6, 13)),
                    Quiz(3, "Флора и фауна", listOf(12, 22, 31)),
                )
                _status.value = QuizAppApiStatus.DONE
            } catch (e: Exception) {
                _quizes.value = listOf()
                _status.value = QuizAppApiStatus.ERROR
                _errorMessage.value = "Произошла ошибка при подключении к серверу: ${e.message}"
            }
        }
    }

    // разница в том, что в одном случае мы работаем с list of ids, в другом list of QuizQuestion
    fun getQuizQuestionsList(quizId: Int) {
        viewModelScope.launch {
            _status.value = QuizAppApiStatus.LOADING
            try {
                //val listResult = QuizAppApi.retrofitService.getQuizQuestions(quizId)
                //_quizQuestions.value = listResult
                _quizQuestions.value = listOf(
                    QuizQuestion(
                        1, "Каким именем назвали рыжего котика?",
                        arrayOf("Рыжик", "Барсик", "Мурзик", "Мурка"),"Рыжик"),
                    QuizQuestion(
                        2, "Каким именем назвали k-pop кошечку?",
                        arrayOf("Кузя", "Китти", "Масяня", "Симба"),"Китти")
                )
                _status.value = QuizAppApiStatus.DONE
            } catch (e: Exception) {
                _quizQuestions.value = listOf()
                _status.value = QuizAppApiStatus.ERROR
                _errorMessage.value = "Произошла ошибка при подключении к серверу: ${e.message}"
            }
        }
    }

    fun getNextQuestion() {

    }

    // set list got from quiz
    fun setQuestionsList() {

    }

    fun getQuestion(questionId: Int) {
        viewModelScope.launch {
            _status.value = QuizAppApiStatus.LOADING
            try {
                //val singleQuestion = QuizAppApi.retrofitService.getQuestion(questionId)
                //_currentQuizQuestion.value = singleQuestion
                _currentQuizQuestion.value = QuizQuestion(
                    1, "Каким именем назвали рыжего котика?",
                    arrayOf("Рыжик", "Барсик", "Мурзик", "Мурка"),"Рыжик")
                _status.value = QuizAppApiStatus.DONE
            } catch (e: Exception) {
                _status.value = QuizAppApiStatus.ERROR
                _errorMessage.value = "Произошла ошибка при подключении к серверу: ${e.message}"
            }
        }
    }

}