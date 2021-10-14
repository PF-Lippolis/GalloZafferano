package com.roostersoft.gallozafferano

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
            val utenti = mapOf("Francesco" to "1", "Alessandro" to "2", "Marco" to "3")
            val username = binding.username.text.toString()
                if(utenti.containsKey(username) && utenti.get(username) == binding.password.text.toString()){
                val intent1 = Intent(this,MainActivity::class.java).apply {
                putExtra("username_inserita", binding.username.text.toString())
            }
                startActivity(intent1)
                }else{Toast.makeText(this,"Username o Password errati", Toast.LENGTH_LONG).show()
            }
        }
    }
}