package com.test.sitec.sitectestlogin.presentation.ui.base

import android.view.View
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment(), View.OnClickListener {

    abstract fun setViews()

    abstract fun setClickListeners()

    fun doNothing() {}
}