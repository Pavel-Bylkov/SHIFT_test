package com.example.myapplication

import android.widget.EditText


class Util {
    companion object {
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
    }
}