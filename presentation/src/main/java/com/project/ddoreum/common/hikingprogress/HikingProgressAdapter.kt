package com.project.ddoreum.common.hikingprogress

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.project.ddoreum.model.UserInfo

class HikingProgressAdapter :
    ListAdapter<UserInfo, HikingProgressViewHolder>(HikingProgressDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HikingProgressViewHolder {
        return HikingProgressViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: HikingProgressViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        object HikingProgressDiffUtil : DiffUtil.ItemCallback<UserInfo>() {
            override fun areItemsTheSame(
                oldItem: UserInfo,
                newItem: UserInfo
            ): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(
                oldItem: UserInfo,
                newItem: UserInfo
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}