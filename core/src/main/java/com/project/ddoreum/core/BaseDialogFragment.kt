package com.project.ddoreum.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment

abstract class BaseDialogFragment<T : ViewDataBinding>(@LayoutRes private val layoutId: Int) :
    DialogFragment() {

    lateinit var binding: T

    protected abstract val viewModel: BaseViewModel

    lateinit var listener: (Any?) -> Unit
    open val isListenerInitialized: Boolean
        get() = this::listener.isInitialized

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initLayout()
    }

    protected abstract fun initLayout()

    protected fun bind(action: T.() -> Unit) {
        binding.run(action)
    }

}