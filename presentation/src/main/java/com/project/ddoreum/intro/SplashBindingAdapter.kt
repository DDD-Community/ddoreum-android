package com.project.ddoreum.intro

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.project.ddoreum.R

@BindingAdapter("bind:selected")
fun View.bindSelected(isSelected: Boolean) {
    this.isSelected = isSelected
}

@BindingAdapter("bind:visible")
fun View.bindVisible(isVisible: Boolean) {
    this.isVisible = isVisible
}

@BindingAdapter("bind:splash_background_color")
fun ConstraintLayout.bindSplashBackgroundColor(isFirst: Boolean) {
    this.setBackgroundColor(
        if (isFirst) {
            context.getColor(R.color.orange)
        } else {
            context.getColor(R.color.white)
        }
    )
}