package za.co.varsitycollege.assignment2_st10489984

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Flashcard_Page : AppCompatActivity() {
    enum class Level {
        Easy,
        Medium,
        Hard,
        Expert
    }
    val easy = arrayOf<String>("William Shakespeare was a playwright, a poet, and a actor during the Elizabethan era",
        "Egypt, Jordan, and Syria have all invaded Israel", "World War II ended in 1945",
        "Humans were partly responsible for the extinction of dinosaurs ","The Hundred Years' War lasted less than 100 years")

    val medium = arrayOf<String>("The Battle of Hastings took place in 1866", "Timothée Chalamet went to the same school as Nicki Minaj",
        "Before becoming queen, Queen Elizabeth II trained as a mechanic", "The first animal sent into space was a monkey",
        "Titanic was released in 1999")

    val hard = arrayOf<String>("The Roman Empire was founded by Romulus and Remus in 753 BC", "The invention of paper originated in China",
        "The Black Death was a disease caused by the bubonic plague, which swept through Europe in the 14th century ",
        "The Parthenon was built as a temple dedicated to Zeus", "The ancient Greek city-state of Sparta was known for its democratic government")

    val expert = arrayOf<String>("Cleopatra was the last pharaoh of the Ptolemaic dynasty", "The invention of writing id credited to the Sumerians ",
        "World War I was initiated by the assassination of Archduke Franz Ferdinand ", "The Roman Empire's influence stretched to the present-day ",
        "Leonardo da Vinci's Mona Lisa was painted during the early Renaissance")

    val answers = arrayOf<String>("True", "True", "True", "False", "False")

    val userInput = arrayOf<String>("", "", "", "", "")

    var currentLevelQuestions = arrayOf<String>()

    var currentQuestionIndex = 0

    var quizStared = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_flashcard_page)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val levelDifficultySpinner = findViewById<Spinner>(R.id.levelDifficultySpinner)
        levelDifficultySpinner.adapter=ArrayAdapter<Flashcard_Page.Level>(this,android.R.layout.simple_list_item_1,Level.values())

        val startQuizButton = findViewById<Button>(R.id.startQuizButton)
        val trueButton = findViewById<Button>(R.id.trueButton)
        val falseButton = findViewById<Button>(R.id.falseButton)
        val previousQuestionButton = findViewById<Button>(R.id.previousQuestionButton)
        val nextQuestionButton = findViewById<Button>(R.id.nextQuestionButton)
        val getFeedbackButton = findViewById<Button>(R.id.getFeedbackButton)

        val gradeEnterButton = findViewById<EditText>(R.id.gradeEnterEditText)
        val historyQuestionTextView = findViewById<TextView>(R.id.historyQuestionTextView)

        historyQuestionTextView.visibility = View.GONE
        previousQuestionButton.visibility = View.GONE
        nextQuestionButton.visibility = View.GONE
        trueButton.visibility = View.GONE
        falseButton.visibility = View.GONE

        startQuizButton?.setOnClickListener {
            val userGrade = gradeEnterButton.text.toString()
            if (userGrade.isBlank()) {
                Toast.makeText(this, "Please enter your grade to start", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
        }

        startQuizButton.visibility = View.GONE
        gradeEnterButton.visibility = View.GONE
        levelDifficultySpinner.isEnabled = false

        currentLevelQuestions = when (levelDifficultySpinner.selectedItem as Level) {
            Level.Easy -> easy
            Level.Medium -> medium
            Level.Hard -> hard
            Level.Expert -> expert
        }

        quizStared = true
        currentQuestionIndex = 0

        historyQuestionTextView.visibility = View.VISIBLE
        trueButton.visibility = View.VISIBLE
        falseButton.visibility = View.VISIBLE
        previousQuestionButton.visibility = View.VISIBLE
        nextQuestionButton.visibility = View.VISIBLE

        previousQuestionButton.isEnabled = false

        trueButton?.setOnClickListener {
            if (!quizStared) return@setOnClickListener
            userInput[currentQuestionIndex] = "True"
            Toast.makeText(this,"You selected TRUE", Toast.LENGTH_SHORT).show()
        }

        falseButton?.setOnClickListener {
            if (!quizStared) return@setOnClickListener
            userInput[currentQuestionIndex] = "False"
            Toast.makeText(this,"You selected FALSE", Toast.LENGTH_SHORT).show()
        }

        nextQuestionButton?.setOnClickListener {
            if (!quizStared) return@setOnClickListener
            if (currentQuestionIndex < currentLevelQuestions.size -1) {
                currentQuestionIndex++
                historyQuestionTextView.text = "Question ${currentQuestionIndex + 1}: ${currentLevelQuestions[currentQuestionIndex]}"
                previousQuestionButton.isEnabled = true
                nextQuestionButton.isEnabled = currentQuestionIndex < currentLevelQuestions.size -1

            }
        }

        previousQuestionButton?.setOnClickListener {
            if (!quizStared) return@setOnClickListener
            if (currentQuestionIndex > 0) {
                currentQuestionIndex--
                historyQuestionTextView.text = "Question ${currentQuestionIndex + 1}: ${currentLevelQuestions[currentQuestionIndex]}"
                nextQuestionButton.isEnabled = true
                previousQuestionButton.isEnabled = currentQuestionIndex > 0
            }
        }

    }
}