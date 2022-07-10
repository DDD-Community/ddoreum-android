package com.project.ddoreum.challenge

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.project.ddoreum.R
import com.project.ddoreum.common.viewBind
import com.project.ddoreum.databinding.LayoutChallengeItemBinding
import com.project.ddoreum.databinding.LayoutMountainInfoItemBinding
import com.project.ddoreum.domain.entity.challenge.ChallengeInfoData
import com.project.ddoreum.domain.entity.mountain.MountainInfoData

class PeriodChallengeListAdapter(
    private val onClick: (ChallengeInfoData) -> Unit
) : ListAdapter<ChallengeInfoData, PeriodChallengeListAdapter.MountainInfoViewHolder>(PeriodChallengeDiffUtil) {

    companion object {
        object PeriodChallengeDiffUtil : DiffUtil.ItemCallback<ChallengeInfoData>() {
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

    class MountainInfoViewHolder(val binding: LayoutChallengeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun onBind(
            item: ChallengeInfoData,
            onClick: (ChallengeInfoData) -> Unit
        ) {
            binding.data = item
            binding.tvChallengeName.text = item.name
            binding.tvChallengeCount.text = "${item.count}명 도전 중"
            itemView.setOnClickListener { onClick.invoke(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MountainInfoViewHolder {
        return MountainInfoViewHolder(viewBind(parent, R.layout.layout_challenge_item))
    }

    override fun onBindViewHolder(holder: MountainInfoViewHolder, position: Int) {
        return holder.onBind(getItem(position), onClick)
    }
}