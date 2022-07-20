package com.project.ddoreum.record

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.ddoreum.databinding.ItemRecordBinding
import com.project.ddoreum.model.Record

class RecordViewHolder(
    private val binding: ItemRecordBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Record) {
        binding.item = item
    }

    companion object {
        fun create(parent: ViewGroup): RecordViewHolder {
            val binding = ItemRecordBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

            return RecordViewHolder(binding)
        }
    }
}
