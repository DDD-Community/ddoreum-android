package com.project.ddoreum.intro.permission

import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.project.ddoreum.R
import com.project.ddoreum.common.highLightWord

@BindingAdapter("bind:permission_essential_tag", "bind:permission_is_essential")
fun TextView.bindPermissionEssentialTag(fullText: String?, isEssential: Boolean?) {
    val tag = if (isEssential == true) "(필수)" else "(선택)"

    if (isEssential == true) {
        this.text =
            "$fullText $tag".highLightWord(tag, ContextCompat.getColor(context, R.color.orange))

    } else {
        this.text =
            "$fullText $tag".highLightWord(tag, ContextCompat.getColor(context, R.color.gray_04))
    }
}

@BindingAdapter("bind:image_src")
fun ImageView.bindImageSource(
    @DrawableRes src: Int?
) {
    src?.let {
        setImageDrawable(ContextCompat.getDrawable(this.context, src))
    }
}