package com.tylernettleton.geoquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.view.*

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button
    private lateinit var previousButton: Button
    private lateinit var cheatButton: Button
    private lateinit var questionText: TextView


    private val quizViewModel: QuizViewModel by lazy {
        ViewModelProviders.of(this).get(QuizViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "onCreate(Bundle?) called")

        setContentView(R.layout.activity_main)


        // Find buttons by resource ID and assign to private class variables
        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        previousButton = findViewById(R.id.previous_button)
        questionText = findViewById(R.id.question_text)
        cheatButton = findViewById(R.id.cheat_button)

        setupListeners()
        updateQuestion()
    }


    private fun updateQuestion() {
        questionText.setText(quizViewModel.currentQuestionText)
    }

    private fun checkAnswer(answer: Boolean) {

        val messageResId = if (quizViewModel.currentQuestionAnswer == answer) {
            quizViewModel.nextQuestion()
            R.string.correct_toast
        } else {
            R.string.correct_toast
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
    }

    private fun setupListeners() {
        trueButton.setOnClickListener {v: View? ->
            checkAnswer(true)
        }

        falseButton.setOnClickListener { v: View? ->
            checkAnswer(false)
        }

        nextButton.setOnClickListener { v: View? ->
            quizViewModel.nextQuestion()
            updateQuestion()
        }

        questionText.setOnClickListener { v: View? ->
            quizViewModel.nextQuestion()
            updateQuestion()
        }

        previousButton.setOnClickListener { v: View? ->
            quizViewModel.previousQuestion()
            updateQuestion()
        }

        cheatButton.setOnClickListener { v: View? ->
            val intent = Intent(this, CheatActivity::class.java)
            startActivity(intent)
        }
    }



    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }






}
