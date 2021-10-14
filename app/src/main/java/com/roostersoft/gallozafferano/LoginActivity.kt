package com.roostersoft.gallozafferano

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.roostersoft.gallozafferano.databinding.ActivityLoginBinding
import com.roostersoft.gallozafferano.databinding.ActivityMainBinding

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onButtonClick()
    }

    fun onButtonClick() {
        binding.buttonAccedi.setOnClickListener {
            val utenti = mapOf("francesco@gmail.com" to ("Francesco" to "1"), "alessandro@gmail.com" to ("Alessandro" to "2"), "marco@gmail.com" to ("Marco" to "3"))
            val username = binding.username.text.toString()
            var info = utenti.get(username)
            if(info!=null && info.second == binding.password.text.toString()){
                    val userSharedPref = getSharedPreferences(
                        "user", Context.MODE_PRIVATE)
                    userSharedPref.edit().putString("user", info.first).apply()

                    val intent1 = Intent(this,MainActivity::class.java).apply {
                        putExtra("username_inserita", info.first)
                    }
                    startActivity(intent1)
                }else{Toast.makeText(this,"Username o Password errati", Toast.LENGTH_LONG).show()
            }
        }
    }
}