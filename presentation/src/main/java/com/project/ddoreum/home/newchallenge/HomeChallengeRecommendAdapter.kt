package com.project.ddoreum.home.newchallenge

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.project.ddoreum.domain.entity.challenge.ChallengeInfoData

class HomeChallengeRecommendAdapter(
    private val listener: (Int) -> Unit
) :
    ListAdapter<ChallengeInfoData, HomeChallengeRecommendViewHolder>(HomeMountainRecommendDiffUtil) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeChallengeRecommendViewHolder {
        return HomeChallengeRecommendViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: HomeChallengeRecommendViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }

    companion object {
        object HomeMountainRecommendDiffUtil : DiffUtil.ItemCallback<ChallengeInfoData>() {
            override fun areItemsTheSame(
                oldItem: ChallengeInfoData,
                newItem: ChallengeInfoData
            ): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(
                oldItem: ChallengeInfoData,
                newItem: ChallengeInfoData
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}