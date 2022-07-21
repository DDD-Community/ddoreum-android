package com.project.ddoreum.home.newchallenge

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.ddoreum.databinding.ItemHomeChallengeRecommendBinding
import com.project.ddoreum.domain.entity.challenge.ChallengeInfoData

class HomeChallengeRecommendViewHolder(
    private val binding: ItemHomeChallengeRecommendBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ChallengeInfoData, listener: (Int) -> Unit) {
        binding.item = item

        binding.root.setOnClickListener {
            listener.invoke(item.id)
        }
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
