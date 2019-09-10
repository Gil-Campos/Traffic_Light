package com.example.trafficlight

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class NewActivity : AppCompatActivity() {

    private var handler: Handler = Handler()
    private var flag: Boolean = true
    private var counter: Int = 0
    private val text = arrayListOf("No Pasar!", "Pase con Precaucion", "Es Seguro Pasar!")
    private val colors = arrayListOf("#fc0303", "#fcf403", "#45fc03")
    private lateinit var textViewMessage: TextView
    private lateinit var relativeLayout: RelativeLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)

        textViewMessage = findViewById(R.id.textView)
        relativeLayout = findViewById(R.id.relativeLayout)

        val stopButton = findViewById<Button>(R.id.button)
        stopButton.setOnClickListener {

            if (flag) {
                relativeLayout.setBackgroundColor(Color.parseColor("#808080"))
                stopButton.text = "Seguir"
                handler.removeCallbacks(runnable)
                flag = false
            } else {
                stopButton.text = "Detener"
                runnable.run()
                flag = true
            }
        }

    }

    override fun onStart() {
        super.onStart()
        runnable.run()
    }

    override fun onStop() {
        super.onStop()
        handler.removeCallbacks(runnable)
    }

    fun back() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }


    fun textMessage(num: Int) {
        textViewMessage.text = text[num]
        relativeLayout.setBackgroundColor(Color.parseColor(colors[num]))
    }

    private val runnable = object : Runnable {
        override fun run() {
            when (counter) {
                0 -> {
                    textMessage(0)
                    counter++
                }
                1 -> {
                    textMessage(1)
                    counter++
                }
                2 -> {
                    textMessage(2)
                    counter++
                }
                3 -> {
                    back()
                }
            }
            handler.postDelayed(this, 5000)
        }
    }

}
