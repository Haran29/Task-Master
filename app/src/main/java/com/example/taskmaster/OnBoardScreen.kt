package com.example.taskmaster

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class OnBoardScreen : AppCompatActivity() {
    private val DELAYTIME: Long = 3000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_board_screen)

        Handler(Looper.getMainLooper()).postDelayed({

            val intent = Intent(this,TaskListPage::class.java)
            startActivity(intent)
            finish()
        }, DELAYTIME)
    }
}