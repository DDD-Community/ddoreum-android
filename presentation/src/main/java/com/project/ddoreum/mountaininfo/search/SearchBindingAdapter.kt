package com.project.ddoreum.mountaininfo.search

import androidx.appcompat.widget.SearchView
import androidx.databinding.BindingAdapter
import com.project.ddoreum.mountaininfo.MountainInfoViewModel

@BindingAdapter("setTextChangeListener")
fun SearchView.setTextChangeListener(viewModel: MountainInfoViewModel) {
    setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            return false
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            if (!newText.isNullOrBlank()) {
                viewModel.updateSearchKeyword(newText)
            }
            return true
        }
    })
}
