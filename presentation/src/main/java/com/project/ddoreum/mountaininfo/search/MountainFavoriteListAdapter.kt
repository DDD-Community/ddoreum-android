package com.project.ddoreum.mountaininfo.search

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.project.ddoreum.R
import com.project.ddoreum.common.viewBind
import com.project.ddoreum.databinding.LayoutFavoriteMountainInfoItemBinding
import com.project.ddoreum.domain.entity.mountain.MountainDetailInfoData

class MountainFavoriteListAdapter(
    private val onClick: (MountainDetailInfoData) -> Unit
) : ListAdapter<MountainDetailInfoData, MountainFavoriteListAdapter.MountainInfoViewHolder>(MountainInfoDiffUtil) {

    companion object {
        object MountainInfoDiffUtil : DiffUtil.ItemCallback<MountainDetailInfoData>() {
            override fun areItemsTheSame(
                oldItem: MountainDetailInfoData,
                newItem: MountainDetailInfoData
            ): Boolean {
                return oldItem.mountainCode == newItem.mountainCode
            }

            override fun areContentsTheSame(
                oldItem: MountainDetailInfoData,
                newItem: MountainDetailInfoData
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    class MountainInfoViewHolder(val binding: LayoutFavoriteMountainInfoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun onBind(
            item: MountainDetailInfoData,
            onClick: (MountainDetailInfoData) -> Unit
        ) {
            binding.data = item
            binding.tvMountainElevation.text = "${item.elevation}m"
            itemView.setOnClickListener { onClick.invoke(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MountainInfoViewHolder {
        return MountainInfoViewHolder(viewBind(parent, R.layout.layout_favorite_mountain_info_item))
    }

    override fun onBindViewHolder(holder: MountainInfoViewHolder, position: Int) {
        return holder.onBind(getItem(position), onClick)
    }
}