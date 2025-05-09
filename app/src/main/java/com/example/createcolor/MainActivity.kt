package com.example.createcolor

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.example.createcolor.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var colorDisplay: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val showInputDialogButton = findViewById<Button>(R.id.showInputDialogButton)
        colorDisplay = findViewById(R.id.color_display)

        showInputDialogButton.setOnClickListener {
            showInputDialog()
        }
    }

    private fun showInputDialog(){
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_rgb, null)
        val Red = dialogView.findViewById<EditText>(R.id.red)
        val Green = dialogView.findViewById<EditText>(R.id.green)
        val Blue = dialogView.findViewById<EditText>(R.id.blue)

        val dialog = AlertDialog.Builder(this)
            .setTitle("Выбор цвета (RGB)")
            .setView(dialogView)
            .setPositiveButton("OK") { _, _ ->
                val r = Red.text.toString().toIntOrNull()?.coerceIn(0, 255) ?: 0
                val g = Green.text.toString().toIntOrNull()?.coerceIn(0, 255) ?: 0
                val b = Blue.text.toString().toIntOrNull()?.coerceIn(0, 255) ?: 0
                colorDisplay.setBackgroundColor(Color.rgb(r, g, b))
            }
            .setNegativeButton("Отмена", null)
            .create()

        dialog.show()
    }

}