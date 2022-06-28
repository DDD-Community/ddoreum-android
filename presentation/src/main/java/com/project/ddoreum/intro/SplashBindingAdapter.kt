package com.project.ddoreum.intro

import android.graphics.drawable.TransitionDrawable
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter

@BindingAdapter("bind:selected")
fun View.bindSelected(isSelected: Boolean) {
    this.isSelected = isSelected
}

@BindingAdapter("bind:visible")
fun View.bindVisible(isVisible: Boolean) {
    this.isVisible = isVisible
}

@BindingAdapter("bind:splash_background")
fun ConstraintLayout.bindSplashBackgroundColor(isFirst: Boolean) {
    if (isFirst) (background as TransitionDrawable).startTransition(1000)
}