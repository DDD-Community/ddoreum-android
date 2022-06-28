package com.project.ddoreum.intro.permission

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.ddoreum.databinding.ItemSplashPermissionBinding
import com.project.ddoreum.model.PermissionInformation

class SplashPermissionViewHolder(
    private val binding: ItemSplashPermissionBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: PermissionInformation) {
        binding.item = item
    }

    companion object {
        fun create(parent: ViewGroup): SplashPermissionViewHolder {
            val binding = ItemSplashPermissionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

            return SplashPermissionViewHolder(binding)
        }
    }
}
