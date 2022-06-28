package com.project.ddoreum.common

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan

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
