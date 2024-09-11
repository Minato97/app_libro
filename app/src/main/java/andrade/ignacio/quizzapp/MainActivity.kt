package andrade.ignacio.quizzapp

import andrade.ignacio.quizzapp.databinding.ActivityMainBinding
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

//    private lateinit var trueButton: Button
//    private lateinit var falseButton: Button

    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true)
    )
    private var currentIndex = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets


        }
//        trueButton = findViewById(R.id.true_button)
//        falseButton = findViewById(R.id.false_button)

//        trueButton.setOnClickListener { view: View ->
        binding.trueButton.setOnClickListener { view: View ->
            // Do something in response to the click here
//            Toast.makeText(
//                this,
//                R.string.correct_toast,
//                Toast.LENGTH_SHORT
//            ).show()
            checkAnswer(true)
        }
//        falseButton.setOnClickListener { view: View ->
        binding.falseButton.setOnClickListener { view: View ->
            // Do something in response to the click here
//            Toast.makeText(
//                this,
//                R.string.incorrect_toast,
//                Toast.LENGTH_SHORT
//            ).show()
            checkAnswer(false)

        }

        binding.nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()

//            val questionTextResId = questionBank[currentIndex].questionText
//            binding.questionTextView.setText(questionTextResId)
        }
        binding.prevButton.setOnClickListener {
            // Restar 1 y asegurarse de que no sea negativo usando la lógica modular
            currentIndex = if (currentIndex - 1 < 0) questionBank.size - 1 else currentIndex - 1
            updateQuestion()
        }

        updateQuestion()

//        val questionTextResId = questionBank[currentIndex].questionText
//        binding.questionTextView.setText(questionTextResId)


    }

    private fun updateQuestion() {
        val questionTextResId = questionBank[currentIndex].questionText
        binding.questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer
        val messageResId = if (userAnswer == correctAnswer) {
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
            .show()
    }

}