package com.project.ddoreum.home.challenge

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

class HomeChallengeAdapter :
    ListAdapter<Int, HomeChallengeViewHolder>(HomeChallengeDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeChallengeViewHolder {
        return HomeChallengeViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: HomeChallengeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        object HomeChallengeDiffUtil : DiffUtil.ItemCallback<Int>() {
            override fun areItemsTheSame(
                oldItem: Int,
                newItem: Int
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: Int,
                newItem: Int
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}