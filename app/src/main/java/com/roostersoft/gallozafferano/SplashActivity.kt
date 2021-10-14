package com.roostersoft.gallozafferano

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent: Intent
        val handler = Handler(Looper.getMainLooper())
        //setContentView(R.layout.activity_splash)
        //valore da salvare - shared preferences
        val userSharedPref = getSharedPreferences(
            "user", Context.MODE_PRIVATE)
        if (userSharedPref.getString("user", null)==null){
            intent = Intent(this,LoginActivity::class.java)
        }else{
            intent = Intent(this, MainActivity::class.java)
        }
        handler.postDelayed(Runnable {
            // Delay per vedere meglio lo splash
            startActivity(intent)
            finish()
        }, 2000)
    }
}