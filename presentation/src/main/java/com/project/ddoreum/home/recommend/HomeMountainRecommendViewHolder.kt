package com.project.ddoreum.home.recommend

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.ddoreum.databinding.ItemHomeMountainRecommendBinding
import com.project.ddoreum.domain.entity.mountain.MountainInfoData

class HomeMountainRecommendViewHolder(
    private val binding: ItemHomeMountainRecommendBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: MountainInfoData, listener: (String?) -> Unit) {
        binding.item = item
        binding.root.setOnClickListener {
            listener.invoke(item.mountainInfo.name)
        }
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
