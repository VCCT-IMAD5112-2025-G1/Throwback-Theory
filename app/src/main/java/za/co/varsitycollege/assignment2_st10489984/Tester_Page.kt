package za.co.varsitycollege.assignment2_st10489984

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Tester_Page : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tester_page)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val actualQuizButton=findViewById<Button>(R.id.actualQuizButton)

        actualQuizButton.setOnClickListener {
            Toast.makeText(this,"Goodluck!",Toast.LENGTH_LONG).show()
            val intent = Intent(this,Flashcard_Page::class.java)
            startActivity(intent)
        }
    }
}