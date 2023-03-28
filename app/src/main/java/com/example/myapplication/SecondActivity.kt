package com.example.myapplication

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView


class SecondActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_activity)

        findViewById<Button>(R.id.ok).setOnClickListener {
            finish()
        }
        val data = intent.getStringArrayListExtra("data")

        if (data != null) {
            data.forEach {
                val keyValue = it.split(":")
                val key = keyValue[0]
                val value = keyValue[1]
                if (key == "bin") {
                    findViewById<TextView>(R.id.bin).text = value
                } else if (key == "scheme") {
                    findViewById<TextView>(R.id.scheme).text = value
                } else if (key == "brand") {
                    findViewById<TextView>(R.id.brand).text = value
                } else if (key == "number_length") {
                    findViewById<TextView>(R.id.number_length).text = value
                } else if (key == "number_luhn") {
                    val str = if (value == "true") "Yes" else "No"
                    findViewById<TextView>(R.id.number_luhn).text = str
                } else if (key == "type") {
                    findViewById<TextView>(R.id.type).text = value
                } else if (key == "prepaid") {
                    val str = if (value == "true") "Yes" else "No"
                    findViewById<TextView>(R.id.prepaid).text = str
                } else if (key == "country_alpha2") {
                    findViewById<TextView>(R.id.country_alpha2).text = value
                } else if (key == "country_name") {
                    findViewById<TextView>(R.id.country_name).text = value
                } else if (key == "country_latitude") {
                    findViewById<TextView>(R.id.country_latitude).text = value
                } else if (key == "country_longitude") {
                    findViewById<TextView>(R.id.country_longitude).text = value
                } else if (key == "bank_name") {
                    findViewById<TextView>(R.id.bank_name).text = value
                } else if (key == "bank_city") {
                    findViewById<TextView>(R.id.bank_city).text = value
                } else if (key == "bank_url") {
                    findViewById<TextView>(R.id.bank_url).text = value
                } else if (key == "bank_phone") {
                    findViewById<TextView>(R.id.bank_phone).text = value
                }
            }
        }
    }
}

