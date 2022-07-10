package com.project.ddoreum.mountaininfo.search.areatype

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.project.ddoreum.R
import com.project.ddoreum.common.viewBind
import com.project.ddoreum.databinding.LayoutAreaTypeRegionBinding

class AreaTypeListAdapter(
    private val onClick: (String) -> Unit
) : ListAdapter<String, AreaTypeListAdapter.AreaTypeRegionViewHolder>(
    MountainInfoDiffUtil
) {

    private var _prevSelectPosition: String = "서울특별시"

    companion object {
        val areaList = mutableListOf<String>(
            "서울특별시","경기도","부산광역시","인천광역시","대구광역시","대전광역시","강원도","광주광역시","충청남도","충청북도","전라북도","전라남도","세종특별자치시",
            "경상북도","경상남도","제주특별자치도")
        object MountainInfoDiffUtil : DiffUtil.ItemCallback<String>() {
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

    class AreaTypeRegionViewHolder(val binding: LayoutAreaTypeRegionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun onBind(
            item: String,
            prevSelectPosition: String,
            onClick: (String) -> Unit,
            onPositionChanged: (String) -> Unit
        ) {
            if (prevSelectPosition == item) {
                binding.tvArea.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.white))
            } else {
                binding.tvArea.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.gray_01))
            }
            binding.tvArea.text = item
            itemView.setOnClickListener {
                onClick.invoke(item)
                onPositionChanged.invoke(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AreaTypeRegionViewHolder {
        return AreaTypeRegionViewHolder(viewBind(parent, R.layout.layout_area_type_region))
    }

    override fun onBindViewHolder(holder: AreaTypeRegionViewHolder, position: Int) {
        return holder.onBind(getItem(position), _prevSelectPosition, onClick) {
            _prevSelectPosition = it
        }
    }
}