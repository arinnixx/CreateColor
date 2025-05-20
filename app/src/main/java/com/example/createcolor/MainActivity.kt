package com.example.createcolor

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.core.graphics.red
import com.example.createcolor.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var colorDisplay: View


    private val sharedPreferences by lazy {
        getSharedPreferences("color", MODE_PRIVATE)
    }

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
        val Display = dialogView.findViewById<View>(R.id.color_display2)
        val red=sharedPreferences.getInt("red",0)
        val green=sharedPreferences.getInt("green",0)
        val blue=sharedPreferences.getInt("blue",0)

        //установка сохраненных значений
        Red.setText(red.toString())
        Green.setText(green.toString())
        Blue.setText(blue.toString())
        Display.setBackgroundColor(Color.rgb(red,green,blue))

        //отслеживание изменения текста и изменение цвета в доп. окне
        Red.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val r = Red.text.toString().toIntOrNull()?.coerceIn(0, 255) ?: 0
                val g = Green.text.toString().toIntOrNull()?.coerceIn(0, 255) ?: 0
                val b = Blue.text.toString().toIntOrNull()?.coerceIn(0, 255) ?: 0
                Display.setBackgroundColor(Color.rgb(r, g, b))
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        Green.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val r = Red.text.toString().toIntOrNull()?.coerceIn(0, 255) ?: 0
                val g = Green.text.toString().toIntOrNull()?.coerceIn(0, 255) ?: 0
                val b = Blue.text.toString().toIntOrNull()?.coerceIn(0, 255) ?: 0
                Display.setBackgroundColor(Color.rgb(r, g, b))
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })

        Blue.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val r = Red.text.toString().toIntOrNull()?.coerceIn(0, 255) ?: 0
                val g = Green.text.toString().toIntOrNull()?.coerceIn(0, 255) ?: 0
                val b = Blue.text.toString().toIntOrNull()?.coerceIn(0, 255) ?: 0
                Display.setBackgroundColor(Color.rgb(r, g, b))
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        //создание диалогового окна
        val dialog = AlertDialog.Builder(this)
            .setTitle("Выбор цвета (RGB)")
            .setView(dialogView)
            .setPositiveButton("OK") { _, _ ->
                val r = Red.text.toString().toIntOrNull()?.coerceIn(0, 255) ?: 0
                val g = Green.text.toString().toIntOrNull()?.coerceIn(0, 255) ?: 0
                val b = Blue.text.toString().toIntOrNull()?.coerceIn(0, 255) ?: 0
                colorDisplay.setBackgroundColor(Color.rgb(r, g, b))

                //сохранение значений
                val editor=sharedPreferences.edit()
                    editor.putInt("red",r)
                    editor.putInt("green",g)
                    editor.putInt("blue",b)
                    editor.apply()


            }
            .setNegativeButton("Отмена", null)
            .create()

        dialog.show()
    }

}