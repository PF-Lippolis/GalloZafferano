package com.roostersoft.gallozafferano

import android.Manifest
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.roostersoft.gallozafferano.viewModel.RecipeViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var logout: ImageButton = findViewById(R.id.logout)
        logout.setOnClickListener {
            //TODO() inserire dialog
            MaterialAlertDialogBuilder(this)
                .setTitle("Logout")
                .setMessage("Sicuro di voler uscire?")
                .setPositiveButton("Si", {dialog, which->
                    val intent = Intent(this,LoginActivity::class.java)
                    startActivity(intent)
                    dialog.dismiss()})
                .setNegativeButton("No", {dialog, which -> dialog.dismiss()})
                .show()
        }

    }
}