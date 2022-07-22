package com.project.ddoreum.home.recommend

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.project.ddoreum.domain.entity.mountain.MountainInfoData

class HomeMountainRecommendAdapter(
    private val listener: (String?) -> Unit
) : ListAdapter<MountainInfoData, HomeMountainRecommendViewHolder>(HomeMountainRecommendDiffUtil) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeMountainRecommendViewHolder {
        return HomeMountainRecommendViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: HomeMountainRecommendViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }

    companion object {
        object HomeMountainRecommendDiffUtil : DiffUtil.ItemCallback<MountainInfoData>() {
            override fun areItemsTheSame(
                oldItem: MountainInfoData,
                newItem: MountainInfoData
            ): Boolean {
                return oldItem.mountainInfo.mountainCode == newItem.mountainInfo.mountainCode
            }

            override fun areContentsTheSame(
                oldItem: MountainInfoData,
                newItem: MountainInfoData
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}