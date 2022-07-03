package com.project.ddoreum.mountaininfo.detail

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.bumptech.glide.request.target.CustomViewTarget
import com.bumptech.glide.request.transition.Transition
import com.google.android.material.button.MaterialButton
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
fun setDetailImage(view: ImageView, url: String?){
    if(!url.isNullOrEmpty()){
        GlideApp.with(view)
            .asBitmap()
            .load(url)
            .override(view.width, view.height)
            .into(object : CustomViewTarget<ImageView, Bitmap>(view) {
                override fun onLoadFailed(errorDrawable: Drawable?) {}
                override fun onResourceCleared(placeholder: Drawable?) {}
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    var resource = resource
                    val width = view.width
                    if (resource.height > 0){
                        val height = if (view.measuredHeight >= resource.height){
                            view.measuredHeight
                        } else{
                            width * resource.height / resource.height
                        }
                        resource = Bitmap.createScaledBitmap(resource, width, height, false)
                    }
                    view.setImageBitmap(resource)
                }
            })
    }
}