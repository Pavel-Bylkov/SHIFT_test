package com.example.myapplication

import io.reactivex.Observable
import java.net.HttpURLConnection
import java.net.URL
import android.widget.EditText

fun editTextSetContentMemorizeSelection(editText: EditText, charSequence: CharSequence) {
    var selectionStart = editText.selectionStart
    var selectionEnd = editText.selectionEnd
    editText.setText(charSequence.toString())
    if (selectionStart > charSequence.toString().length) {
        selectionStart = charSequence.toString().length
    }
    if (selectionStart < 0) {
        selectionStart = 0
    }
    if (selectionEnd > charSequence.toString().length) {
        selectionEnd = charSequence.toString().length
    }
    if (selectionEnd < 0) {
        selectionEnd = 0
    }
    editText.setSelection(selectionStart, selectionEnd)
}

fun formatStrWithSpaces(can: CharSequence): String? {
    val sb = StringBuffer()
    for (i in 0 until can.length) {
        if (i != 0 && i % 4 == 0) {
            sb.append(' ')
        }
        sb.append(can[i])
    }
    return sb.toString()
}

fun createGetRequest(headerKey:String, headerValue:String, url:String) = Observable.create<String> {
    val urlConnection = URL(url).openConnection() as HttpURLConnection
    urlConnection.setRequestProperty(headerKey, headerValue)
    try {
        urlConnection.connect() // само обращение в сеть

        if (urlConnection.responseCode != HttpURLConnection.HTTP_OK)
            it.onError(RuntimeException(urlConnection.responseMessage))
        else {
            val str = urlConnection.inputStream.bufferedReader().readText()
            it.onNext(str)
        }
    } finally {
        urlConnection.disconnect()
    }
}