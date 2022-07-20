package com.project.ddoreum.record

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.project.ddoreum.model.Record

class RecordAdapter : ListAdapter<Record, RecordViewHolder>(RecordDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordViewHolder {
        return RecordViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecordViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        object RecordDiffUtil : DiffUtil.ItemCallback<Record>() {
            override fun areItemsTheSame(
                oldItem: Record,
                newItem: Record
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: Record,
                newItem: Record
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
