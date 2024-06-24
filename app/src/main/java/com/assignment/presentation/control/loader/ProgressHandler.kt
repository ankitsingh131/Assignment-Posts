package com.assignment.presentation.control.loader

import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.assignment.R
import com.assignment.databinding.LayoutInActivityProgressBarBinding
import com.assignment.presentation.activity.base.BaseActivity

class ProgressHandler(
    private val activity: BaseActivity<*, *>,
    private var cancelable: Boolean = true
) {

    private var clContainerProgressBar: ConstraintLayout

    init {
        val viewGroup: ViewGroup =
            activity.findViewById<View>(android.R.id.content).rootView as ViewGroup
        val binding: LayoutInActivityProgressBarBinding =
            DataBindingUtil.inflate(
                activity.layoutInflater,
                R.layout.layout_in_activity_progress_bar,
                viewGroup,
                false
            )
        clContainerProgressBar = binding.clContainerProgressBar
        viewGroup.addView(binding.root)
    }

    fun isCancelable(): Boolean = cancelable

    fun show(cancelable: Boolean = this.cancelable) {
        this.cancelable = cancelable
        clContainerProgressBar.visibility = View.VISIBLE
    }

    fun hide() {
        clContainerProgressBar.visibility = View.GONE
    }

    fun isShowing(): Boolean = clContainerProgressBar.visibility == View.VISIBLE
}