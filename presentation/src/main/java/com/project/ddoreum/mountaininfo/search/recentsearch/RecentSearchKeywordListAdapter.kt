package com.project.ddoreum.mountaininfo.search.recentsearch

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.project.ddoreum.R
import com.project.ddoreum.common.viewBind
import com.project.ddoreum.databinding.LayoutRecentSearchItemBinding

class RecentSearchKeywordListAdapter(
    private val onClick: (String) -> Unit,
    private val onRemoveClick: (String) -> Unit
) : ListAdapter<String, RecentSearchKeywordListAdapter.RecentSearchKeywordViewHolder>(RecentSearchDiffUtil) {

    companion object {
        object RecentSearchDiffUtil : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(
                oldItem: String,
                newItem: String
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: String,
                newItem: String
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    class RecentSearchKeywordViewHolder(val binding: LayoutRecentSearchItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun onBind(
            item: String,
            onClick: (String) -> Unit,
            onRemoveClick: (String) -> Unit
        ) {
            binding.tvMountainName.text = item
            binding.ivRemove.setOnClickListener { onRemoveClick.invoke(item) }
            itemView.setOnClickListener { onClick.invoke(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentSearchKeywordViewHolder {
        return RecentSearchKeywordViewHolder(viewBind(parent, R.layout.layout_recent_search_item))
    }

    override fun onBindViewHolder(holder: RecentSearchKeywordViewHolder, position: Int) {
        return holder.onBind(getItem(position), onClick, onRemoveClick)
    }
}