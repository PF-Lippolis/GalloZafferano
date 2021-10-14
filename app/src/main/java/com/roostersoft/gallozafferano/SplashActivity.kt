package com.roostersoft.gallozafferano

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_splash)
        val intent = Intent(this,LoginActivity::class.java)
        val handler = Handler()
        handler.postDelayed(Runnable {
            // Delay per vedere meglio lo splash
            startActivity(intent)
            finish()
        }, 2000)
    }
}