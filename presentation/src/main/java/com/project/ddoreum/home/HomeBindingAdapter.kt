package com.project.ddoreum.home

import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.project.ddoreum.R
import com.project.ddoreum.common.highLightWord

@BindingAdapter("bind:home_title_text")
fun AppCompatTextView.bindHomeTitleText(name: String?) {
    name?.let {
        val fullText = context.getString(R.string.home_title, name)

        text = fullText.highLightWord(name, ContextCompat.getColor(context, R.color.black))
    }
}