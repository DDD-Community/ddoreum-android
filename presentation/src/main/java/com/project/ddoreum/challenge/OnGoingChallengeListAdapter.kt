package com.project.ddoreum.challenge

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.project.ddoreum.R
import com.project.ddoreum.common.getAcheivePercent
import com.project.ddoreum.common.viewBind
import com.project.ddoreum.databinding.LayoutChallengeProgressItemBinding
import com.project.ddoreum.domain.entity.challenge.InProgressChallengeData

class OnGoingChallengeListAdapter(
    private val onClick: (InProgressChallengeData) -> Unit
) : ListAdapter<InProgressChallengeData, OnGoingChallengeListAdapter.MountainInfoViewHolder>(PeriodChallengeDiffUtil) {

    companion object {
        object PeriodChallengeDiffUtil : DiffUtil.ItemCallback<InProgressChallengeData>() {
            override fun areItemsTheSame(
                oldItem: InProgressChallengeData,
                newItem: InProgressChallengeData
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: InProgressChallengeData,
                newItem: InProgressChallengeData
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    class MountainInfoViewHolder(val binding: LayoutChallengeProgressItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun onBind(
            item: InProgressChallengeData,
            onClick: (InProgressChallengeData) -> Unit
        ) {
            //30% 달성 (18회/60회)
            binding.data = item
            val acheivePercent = getAcheivePercent(item.type, item.succeedCount, item.verifyList?.size, item.verifyCount)
            val totalCount = if (item.type == "PERIOD") item.verifyCount else item.verifyList?.size
            binding.tvProgressCount.text = "${acheivePercent}% 달성 (${item.succeedCount}회/${totalCount}회)"
            binding.progress.progress = acheivePercent
            binding.tvChallengePeriod.text = "${item.startDate} - ${item.endDate}"

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