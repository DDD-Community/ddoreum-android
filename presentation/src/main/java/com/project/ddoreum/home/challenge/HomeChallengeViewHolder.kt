package com.project.ddoreum.home.challenge

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.ddoreum.databinding.ItemHomeChallengeBinding

class HomeChallengeViewHolder(
    private val binding: ItemHomeChallengeBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Int) {
        binding.progress.progress = item
    }

    companion object {
        fun create(parent: ViewGroup): HomeChallengeViewHolder {
            val binding = ItemHomeChallengeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

            return HomeChallengeViewHolder(binding)
        }
    }
}
