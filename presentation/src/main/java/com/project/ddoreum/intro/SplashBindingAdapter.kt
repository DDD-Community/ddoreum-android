package com.project.ddoreum.intro

import android.graphics.drawable.TransitionDrawable
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.project.ddoreum.common.dp

@BindingAdapter("bind:visible")
fun View.bindVisible(isVisible: Boolean) {
    this.isVisible = isVisible
}

@BindingAdapter("bind:splash_background")
fun ConstraintLayout.bindSplashBackgroundColor(isFirst: Boolean?) {
    if (isFirst == true) (background as TransitionDrawable).startTransition(500)
}