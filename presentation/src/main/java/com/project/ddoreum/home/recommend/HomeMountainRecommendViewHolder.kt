package com.project.ddoreum.home.recommend

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.ddoreum.databinding.ItemHomeMountainRecommendBinding
import com.project.ddoreum.model.MountainRecommend

class HomeMountainRecommendViewHolder(
    private val binding: ItemHomeMountainRecommendBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: MountainRecommend) {
        binding.item = item
    }

    companion object {
        fun create(parent: ViewGroup): HomeMountainRecommendViewHolder {
            val binding = ItemHomeMountainRecommendBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

            return HomeMountainRecommendViewHolder(binding)
        }
    }
}
