package com.project.ddoreum.challenge.detail

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.project.ddoreum.R
import com.project.ddoreum.common.viewBind
import com.project.ddoreum.databinding.LayoutChallengeItemBinding
import com.project.ddoreum.databinding.LayoutMountainInfoItemBinding
import com.project.ddoreum.databinding.LayoutVerifyMountainItemBinding
import com.project.ddoreum.domain.entity.challenge.ChallengeInfoData
import com.project.ddoreum.domain.entity.challenge.ChallengeMountainData
import com.project.ddoreum.domain.entity.mountain.MountainInfoData

class ChallengeVerifyMountainListAdapter(
    private val onClick: (ChallengeMountainData) -> Unit
) : ListAdapter<ChallengeMountainData, ChallengeVerifyMountainListAdapter.MountainInfoViewHolder>(PeriodChallengeDiffUtil) {

    companion object {
        object PeriodChallengeDiffUtil : DiffUtil.ItemCallback<ChallengeMountainData>() {
            override fun areItemsTheSame(
                oldItem: ChallengeMountainData,
                newItem: ChallengeMountainData
            ): Boolean {
                return oldItem.code == newItem.code
            }

            override fun areContentsTheSame(
                oldItem: ChallengeMountainData,
                newItem: ChallengeMountainData
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    class MountainInfoViewHolder(val binding: LayoutVerifyMountainItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun onBind(
            item: ChallengeMountainData,
            onClick: (ChallengeMountainData) -> Unit
        ) {
            binding.data = item
            binding.tvMountainElevation.text = "높이 ${item.elevation}m"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MountainInfoViewHolder {
        return MountainInfoViewHolder(viewBind(parent, R.layout.layout_verify_mountain_item))
    }

    override fun onBindViewHolder(holder: MountainInfoViewHolder, position: Int) {
        return holder.onBind(getItem(position), onClick)
    }
}