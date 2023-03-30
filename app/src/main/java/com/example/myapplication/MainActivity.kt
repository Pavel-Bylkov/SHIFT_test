package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.PixelCopy.Request
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import com.google.gson.Gson
import io.reactivex.disposables.Disposable

class MainActivity : AppCompatActivity() {
    var request:Disposable? = null
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
            Log.v("test", "url: ${url}")
            val o = createGetRequest("Accept-Version", "3", url)
                .map { Gson().fromJson(it, Feed::class.java) }
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

            request = o.subscribe({
                Log.v("test", "scheme: ${it.scheme}")
                Log.v("test", "type: ${it.type}")
                if (it.number != null) {
                    Log.v("test", "number_length: ${it.number.length}")
                    Log.v("test", "number_luhn: ${it.number.luhn}")
                }
                Log.v("test", "brand: ${it.brand}")
                Log.v("test", "prepaid: ${it.prepaid}")
                Log.v("test", "country_name: ${it.country.name}")
                Log.v("test", "country_alpha2: ${it.country.alpha2}")
                Log.v("test", "country_latitude: ${it.country.latitude}")
                Log.v("test", "country_longitude: ${it.country.longitude}")
                Log.v("test", "bank_name: ${it.bank.name}")
                Log.v("test", "bank_city: ${it.bank.city}")
                Log.v("test", "bank_url: ${it.bank.url}")
                Log.v("test", "bank_phone: ${it.bank.phone}")
            }, {
                Log.e("test", "", it)
            })

//            val i = Intent(this, SecondActivity::class.java)
//            startActivity(i)

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
        request?.dispose()
    }
}

data class Feed (
    val number: NumberItem? = null,
    val scheme:String,
    val type:String,
    val brand:String,
    val prepaid:Boolean,
    val country: CountryItem,
    val bank: BankItem
)

data class NumberItem (
    val length:String,
    val luhn:Boolean
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