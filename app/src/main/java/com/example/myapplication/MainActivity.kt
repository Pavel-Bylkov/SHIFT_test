package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.PixelCopy.Request
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {
    var request:Request? = null
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
                val formatStr: String = formatStrWithSpaces(origin).toString()
                if (s.toString() != formatStr) {
                    editTextSetContentMemorizeSelection(vETextBin, formatStr)
                    if (before == 0 && count == 1 && formatStr[vETextBin.getSelectionEnd() - 1] == ' ') {
                        vETextBin.setSelection(vETextBin.getSelectionEnd() + 1)
                    }
                }
            }

            override fun afterTextChanged(s: Editable) {}
        })
        val vListHistory = findViewById<ListView>(R.id.history)

        vBtnStart.setOnClickListener {
            val binNumber = vETextBin.text.toString().replace(" ", "")
            val url = "https://lookup.binlist.net/$binNumber"

            val o = createGetRequest("Accept-Version", "3", url)
                .map { Gson().fromJson(it, Feed::class.java) }
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

            request = o.subscribe({
                                  for (item in it.items)
            }, {})

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

data class Feed (
    val number: ArrayList<NumberItem>,
    val scheme:String,
    val type:String,
    val brand:String,
    val prepaid:String,
    val country: ArrayList<CountryItem>,
    val bank: ArrayList<BankItem>
)

data class NumberItem (
    val length:String,
    val luhn:String
)

data class CountryItem (
    val numeric:String,
    val alpha2:String,
    val name:String,
    val emoji:String,
    val currency:String,
    val latitude:String,
    val longitude:String
)

data class BankItem (
    val name:String,
    val url:String,
    val phone:String,
    val city:String
)