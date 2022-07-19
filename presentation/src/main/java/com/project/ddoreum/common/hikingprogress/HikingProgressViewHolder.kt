package com.project.ddoreum.common.hikingprogress

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.ddoreum.databinding.ItemHikingProgressBinding
import com.project.ddoreum.model.UserInfo

class HikingProgressViewHolder(
    private val binding: ItemHikingProgressBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: UserInfo) {
        binding.progress.progress =
            if (bindingAdapterPosition + 1 == item.hikingLv) {
                ((item.hikingElevation.toDouble() / (item.hikingElevation + item.remainElevation)) * 100).toInt()
            } else if (bindingAdapterPosition + 1 > item.hikingLv) {
                0
            } else {
                100
            }
    }

    companion object {
        fun create(parent: ViewGroup): HikingProgressViewHolder {
            val binding = ItemHikingProgressBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

            return HikingProgressViewHolder(binding)
        }
    }
}
