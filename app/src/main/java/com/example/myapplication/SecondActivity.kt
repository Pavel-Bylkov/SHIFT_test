package com.example.myapplication

import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button

class SecondActivity:Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_activity)
        findViewById<Button>(R.id.ok).setOnClickListener {
            finish()
        }

        val data = intent.getStringArrayListExtra("data")

    }
}