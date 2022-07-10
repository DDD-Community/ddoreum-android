package com.project.ddoreum.mountaininfo.list

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.project.ddoreum.R
import com.project.ddoreum.common.viewBind
import com.project.ddoreum.databinding.LayoutMountainInfoItemBinding
import com.project.ddoreum.domain.entity.mountain.MountainInfoData

class MountainInfoListAdapter(
    private val onClick: (MountainInfoData) -> Unit
) : ListAdapter<MountainInfoData, MountainInfoListAdapter.MountainInfoViewHolder>(
    MountainInfoDiffUtil
) {

    companion object {
        object MountainInfoDiffUtil : DiffUtil.ItemCallback<MountainInfoData>() {
            override fun areItemsTheSame(
                oldItem: MountainInfoData,
                newItem: MountainInfoData
            ): Boolean {
                return oldItem.mountainInfo.name == newItem.mountainInfo.name
            }

            override fun areContentsTheSame(
                oldItem: MountainInfoData,
                newItem: MountainInfoData
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    class MountainInfoViewHolder(val binding: LayoutMountainInfoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun onBind(
            item: MountainInfoData,
            onClick: (MountainInfoData) -> Unit
        ) {
            binding.data = item
            item.mountainInfo.location?.let {
                binding.tvMountainAddress.text = it.split("ㆍ")[0]
            }
            binding.tvMountainElevation.text = "${item.mountainInfo.elevation}m"
            itemView.setOnClickListener { onClick.invoke(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MountainInfoViewHolder {
        return MountainInfoViewHolder(viewBind(parent, R.layout.layout_mountain_info_item))
    }

    override fun onBindViewHolder(holder: MountainInfoViewHolder, position: Int) {
        return holder.onBind(getItem(position), onClick)
    }
}