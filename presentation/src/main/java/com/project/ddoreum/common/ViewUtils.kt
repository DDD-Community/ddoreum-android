package com.project.ddoreum.common

import android.content.res.Resources
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.util.TypedValue

fun String.highLightWord(forceWord: String, color: Int): SpannableString {
    return SpannableString(this).apply {
        setSpan(
            StyleSpan(Typeface.BOLD),
            this.indexOf(forceWord),
            this.indexOf(forceWord) + forceWord.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        setSpan(
            ForegroundColorSpan(color),
            this.indexOf(forceWord),
            this.indexOf(forceWord) + forceWord.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }
}

val Int.dp: Int
    get() {
        val metrics = Resources.getSystem().displayMetrics
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), metrics)
            .toInt()
    }
