package com.project.ddoreum.home.newchallenge

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.ddoreum.databinding.ItemHomeChallengeRecommendBinding
import com.project.ddoreum.databinding.ItemHomeMountainRecommendBinding
import com.project.ddoreum.domain.entity.challenge.ChallengeInfoData
import com.project.ddoreum.model.MountainRecommend

class HomeChallengeRecommendViewHolder(
    private val binding: ItemHomeChallengeRecommendBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ChallengeInfoData) {
        binding.item = item
    }

    companion object {
        fun create(parent: ViewGroup): HomeChallengeRecommendViewHolder {
            val binding = ItemHomeChallengeRecommendBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

            return HomeChallengeRecommendViewHolder(binding)
        }
    }
}
