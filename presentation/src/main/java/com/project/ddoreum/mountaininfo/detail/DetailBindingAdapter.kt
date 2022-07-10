package com.project.ddoreum.mountaininfo.detail

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.bumptech.glide.request.target.CustomViewTarget
import com.bumptech.glide.request.transition.Transition
import com.google.android.material.button.MaterialButton
import com.project.ddoreum.R
import com.project.ddoreum.di.GlideApp
import com.project.ddoreum.domain.entity.mountain.MountainDetailInfoData

@BindingAdapter("bindVisibleAndSetText1")
fun TextView.bindFirstVisibleAndSetText(data: MountainDetailInfoData?) {
    data?.let {
        val result = it.location?.split(" ")?.get(0)
        this.isVisible = !result.isNullOrEmpty()
        if (!result.isNullOrEmpty()) {
            this.text = result
        }
    }
}

@BindingAdapter("bindVisibleAndSetText2")
fun TextView.bindSecondVisibleAndSetText(data: MountainDetailInfoData?) {
    data?.let {
        val result = it.location?.split(" ")?.get(1)
        this.isVisible = !result.isNullOrEmpty()
        if (!result.isNullOrEmpty()) {
            this.text = result
        }
    }
}

@BindingAdapter("loadDetailImage")
fun setDetailImage(view: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        GlideApp.with(view)
            .asBitmap()
            .load(url)
            .thumbnail(0.7f)
            .override(view.width, view.height)
            .into(object : CustomViewTarget<ImageView, Bitmap>(view) {
                override fun onLoadFailed(errorDrawable: Drawable?) {}
                override fun onResourceCleared(placeholder: Drawable?) {}
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    var resource = resource
                    resource = Bitmap.createScaledBitmap(resource, view.width, view.height, false)
                    view.setImageBitmap(resource)
                }
            })
    }
}

@BindingAdapter("setFavoriteMountain")
fun ImageView.setFavoriteMountain(data: MountainDetailInfoData?) {
    data?.let {
        val src = if (it.isFavorite) {
            ContextCompat.getDrawable(context, R.drawable.ic_favorite)
        } else {
            ContextCompat.getDrawable(context, R.drawable.ic_not_favorite)
        }
        setImageDrawable(src)
    }
}