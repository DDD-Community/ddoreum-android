package com.project.ddoreum.intro.permission

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.ddoreum.model.PermissionInformation

class SplashPermissionAdapter : RecyclerView.Adapter<SplashPermissionViewHolder>() {

    private val list = PermissionInformation.list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        SplashPermissionViewHolder.create(parent)

    override fun onBindViewHolder(holder: SplashPermissionViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}
