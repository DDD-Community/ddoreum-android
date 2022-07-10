package com.project.ddoreum.challenge

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.project.ddoreum.R
import com.project.ddoreum.common.viewBind
import com.project.ddoreum.databinding.LayoutChallengeProgressItemBinding
import com.project.ddoreum.domain.entity.challenge.ChallengeInfoData

class OnGoingChallengeListAdapter(
    private val onClick: (ChallengeInfoData) -> Unit
) : ListAdapter<ChallengeInfoData, OnGoingChallengeListAdapter.MountainInfoViewHolder>(PeriodChallengeDiffUtil) {

    companion object {
        object PeriodChallengeDiffUtil : DiffUtil.ItemCallback<ChallengeInfoData>() {
            override fun areItemsTheSame(
                oldItem: ChallengeInfoData,
                newItem: ChallengeInfoData
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: ChallengeInfoData,
                newItem: ChallengeInfoData
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    class MountainInfoViewHolder(val binding: LayoutChallengeProgressItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun onBind(
            item: ChallengeInfoData,
            onClick: (ChallengeInfoData) -> Unit
        ) {
            itemView.setOnClickListener { onClick.invoke(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MountainInfoViewHolder {
        return MountainInfoViewHolder(viewBind(parent, R.layout.layout_challenge_progress_item))
    }

    override fun onBindViewHolder(holder: MountainInfoViewHolder, position: Int) {
        return holder.onBind(getItem(position), onClick)
    }
}