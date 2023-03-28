package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val vBtnStart = findViewById<Button>(R.id.start)
        val vETextBin = findViewById<EditText>(R.id.BIN)
//      обрабатываю ввод и добавляю пробел после 4 цифр
        vETextBin.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val origin = s.toString().replace(" ".toRegex(), "")
                val formatStr: String = Util.formatStrWithSpaces(origin).toString()
                if (s.toString() != formatStr) {
                    Util.editTextSetContentMemorizeSelection(vETextBin, formatStr)
                    if (before == 0 && count == 1 && formatStr[vETextBin.getSelectionEnd() - 1] == ' ') {
                        vETextBin.setSelection(vETextBin.getSelectionEnd() + 1)
                    }
                }
            }

            override fun afterTextChanged(s: Editable) {}
        })
        val vListHistory = findViewById<ListView>(R.id.history)

        vBtnStart.setOnClickListener {
            val text = vETextBin.text.toString()

            val i = Intent(this, SecondActivity::class.java)
            startActivity(i)

        }
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}