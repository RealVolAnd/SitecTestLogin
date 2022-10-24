package com.test.sitec.sitectestlogin.common.utils

import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.util.Patterns

object TextUtilites {
    fun parseServerErrorString(stringList: ArrayList<String>): String {
        var result = ""
        stringList.forEach {
            result += it + "\n"
        }
        return result
    }

    fun getUnderlinedText(text: String): SpannableString {
        val content = SpannableString(text)
        content.setSpan(UnderlineSpan(), 0, text.length, 0)
        return content
    }
}

fun CharSequence?.isValidEmail() =
    !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()