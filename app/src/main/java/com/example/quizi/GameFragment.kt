package com.example.quizi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.quizi.databinding.FragmentGameBinding


class GameFragment : Fragment() {

    data class Question(
        val text: String,
        val answer: List<String>
    )

    private val question: MutableList<Question> = mutableListOf(
        Question(
            text = "What is Android Jetpack?",
            answer = listOf("all of these", "tools", "documentation", "libraries")
        ),
        Question(
            text = "Base class for Layout?",
            answer = listOf("ViewGroup", "ViewSet", "ViewCollection", "ViewRoot")
        ),
        Question(
            text = "Layout for complex Screens?",
            answer = listOf("ConstraintLayout", "GridLayout", "LinearLayout", "FrameLayout")
        ),
        Question(
            text = "Pushing structured data into a Layout?",
            answer = listOf("Data Binding", "Data Pushing", "Set Text", "OnClick")
        ),
        Question(
            text = "Inflate layout in fragments?",
            answer = listOf("onCreateView", "onViewCreated", "onCreateLayout", "onInflateLayout")
        ),
    )

    lateinit var currentquestion: Question
    lateinit var answer: MutableList<String>
    private var questionIndex = 0
    private val numQuestion = ((question.size + 1) / 2).coerceAtMost(3)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentGameBinding>(
            inflater, R.layout.fragment_game, container, false
        )

        randomizeQuestion()

        binding.game = this

        binding.submitButton.setOnClickListener {
            val answerIndex = when {
                binding.rb2.isChecked -> 1
                binding.rb3.isChecked -> 2
                binding.rb4.isChecked -> 3
                else -> 0
            }
            if (answer[answerIndex] == currentquestion.answer[0]) {
                questionIndex++
                if (questionIndex < numQuestion) {
                    currentquestion = question[questionIndex]
                    setQuestion()
                    binding.invalidateAll()
                } else {
                    findNavController().navigate(
                        R.id.action_gameFragment_to_wonFragment,
                        bundleOf("numQuestion" to numQuestion, "questionIndex" to questionIndex)
                    )
                }
            } else {
                findNavController().navigate(R.id.outFragment)
            }
        }

        return binding.root
    }

    private fun setQuestion() {

        currentquestion = question[questionIndex]
        answer = currentquestion.answer.toMutableList()
        answer.shuffle()
//        (activity as AppCompatActivity).supportActionBar?.title =
//            getString(R.string.title_android_trivia_question, questionIndex + 1, numQuestion)

    }

    private fun randomizeQuestion() {
        question.shuffle()
        questionIndex = 0
        setQuestion()
    }

}

