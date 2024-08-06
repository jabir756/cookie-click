package com.example.cookie_clicker

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private var score = 0
    private lateinit var scoreTextView: TextView
    private lateinit var cookieImageView: ImageView
    private lateinit var backgroundMediaPlayer: MediaPlayer
    private lateinit var clickMediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        scoreTextView = findViewById(R.id.score)
        cookieImageView = findViewById(R.id.cookie_img)

        updateScore()

        backgroundMediaPlayer = MediaPlayer.create(this, R.raw.background_sound)
        backgroundMediaPlayer.isLooping = true
        backgroundMediaPlayer.start()

        clickMediaPlayer = MediaPlayer.create(this, R.raw.click_sound)

        cookieImageView.setOnClickListener {
            incrementScore()
            clickMediaPlayer.start()
        }

    }
    private fun incrementScore() {
        score++
        if (score % 10 == 0) {
            score += 10 // Add bonus of 10 points
        }
        updateScore()
    }

    private fun updateScore() {
        scoreTextView.text = score.toString()
    }

    override fun onDestroy() {
        super.onDestroy()
        backgroundMediaPlayer.release()
        clickMediaPlayer.release()
    }
}