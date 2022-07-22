package com.project.ddoreum.common

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.project.ddoreum.R
import com.project.ddoreum.common.hikingprogress.HikingProgressAdapter
import com.project.ddoreum.di.GlideApp
import com.project.ddoreum.model.UserInfo
import java.text.DecimalFormat

@BindingAdapter("bind:selected")
fun View.bindSelected(isSelected: Boolean) {
    this.isSelected = isSelected
}

@BindingAdapter("bind:user_hiking_meter")
fun TextView.bindHikingMeter(meter: Int) {
    text = context.getString(R.string.meter_string, DecimalFormat("###,###").format(meter))
}

@BindingAdapter("bind:user_hiking_remain_meter")
fun TextView.bindHikingRemainMeter(meter: Int) {
    text = context.getString(
        R.string.home_my_climbing_next_level,
        DecimalFormat("###,###").format(meter)
    )
}


@BindingAdapter("bind:user_history_progress")
fun RecyclerView.bindUserHistoryProgress(userInfo: UserInfo) {
    val tempList = mutableListOf(userInfo, userInfo, userInfo, userInfo)
    (adapter as? HikingProgressAdapter)?.submitList(tempList)

}

@BindingAdapter("bind:image_url")
fun ImageView.bindImageUrl(url: String?) {
    GlideApp.with(this.context)
        .load(url)
        .centerCrop()
        .thumbnail(0.1f)
        .transition(withCrossFade())
        .into(this)
}

@BindingAdapter("bind:list_item")
fun RecyclerView.bindListItems(list: List<Any>?) {
    if (adapter != null) {
        (adapter as ListAdapter<Any, *>).submitList(list ?: emptyList())
    }
}