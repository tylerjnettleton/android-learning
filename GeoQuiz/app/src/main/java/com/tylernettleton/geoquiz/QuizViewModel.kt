package com.tylernettleton.geoquiz

import android.util.Log
import androidx.lifecycle.ViewModel

private const val TAG = "QuizeViewModel"

class QuizViewModel: ViewModel() {

    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )

    private var currentIndex = 0


    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId

    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer

    fun nextQuestion() {
        currentIndex = (currentIndex + 1) % questionBank.size
    }

    fun previousQuestion() {
        if (currentIndex != 0) {
            currentIndex = (currentIndex - 1 ) % questionBank.size
        }
    }




}