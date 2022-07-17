package com.project.ddoreum.common

import android.content.Context
import android.content.res.Resources
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.project.ddoreum.domain.entity.challenge.ChallengeMountainData
import java.text.SimpleDateFormat
import java.util.*

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

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun View.showKeyboard(){
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
}

fun <T : ViewDataBinding> viewBind(parent: ViewGroup, layoutRes: Int): T {
    return DataBindingUtil.inflate(
        LayoutInflater.from(parent.context),
        layoutRes,
        parent,
        false
    )
}

fun getYearAfterDate(today: String, numberYear: Int): String {
    return if (!today.isNullOrEmpty()) {
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val calendar = Calendar.getInstance()
        val afterDate = sdf.parse(today)
        calendar.time = afterDate
        calendar.add(Calendar.YEAR, numberYear)
        sdf.format(calendar.time)
    } else {
        ""
    }
}

fun getAcheivePercent(type: String, succeedCount: Int, verifyListSize: Int?, verifyCount: Int?): Int {
    when (type) {
        "PERIOD" -> {
            verifyCount?.let {
                return (succeedCount/it) * 100
            }
            return 0
        }
        "LOCATION" -> {
            verifyListSize?.let {
                return (succeedCount/it) * 100
            }
            return 0
        }
        else -> return 0
    }
}