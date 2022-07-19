package com.project.ddoreum.home.recommend

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.project.ddoreum.model.MountainRecommend

class HomeMountainRecommendAdapter :
    ListAdapter<MountainRecommend, HomeMountainRecommendViewHolder>(HomeMountainRecommendDiffUtil) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeMountainRecommendViewHolder {
        return HomeMountainRecommendViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: HomeMountainRecommendViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        object HomeMountainRecommendDiffUtil : DiffUtil.ItemCallback<MountainRecommend>() {
            override fun areItemsTheSame(
                oldItem: MountainRecommend,
                newItem: MountainRecommend
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: MountainRecommend,
                newItem: MountainRecommend
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}