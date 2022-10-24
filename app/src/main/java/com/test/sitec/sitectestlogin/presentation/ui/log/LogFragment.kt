package com.test.sitec.sitectestlogin.presentation.ui.log

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.test.sitec.sitectestlogin.R
import com.test.sitec.sitectestlogin.common.ALERT_DIALOG_TYPE_ERROR
import com.test.sitec.sitectestlogin.common.utils.AlertUtils
import com.test.sitec.sitectestlogin.databinding.FragmentLogBinding
import com.test.sitec.sitectestlogin.databinding.FragmentSplashBinding
import com.test.sitec.sitectestlogin.presentation.ui.base.BaseFragment
import com.test.sitec.sitectestlogin.presentation.ui.splash.SplashLiveData
import com.test.sitec.sitectestlogin.presentation.ui.splash.SplashViewModel

class LogFragment: BaseFragment(), LogContract.View {

    private var _vb: FragmentLogBinding? = null
    private val vb get() = _vb!!
    private lateinit var presenter: LogContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    doNothing()
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _vb = FragmentLogBinding.inflate(inflater, container, false)
        setViews()
        return vb.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListeners()
        presenter = LogPresenter(this)
    }

    override fun setViews() {}

    override fun setClickListeners() {}

    override fun onClick(p0: View?) {}

    fun showErrorDialog(message: String) {
        AlertUtils().showAlertDialog(
            requireContext(),
            ALERT_DIALOG_TYPE_ERROR,
            message,
            "Reply", "Close App", {

            }, {  }
        )
    }

    companion object {
        fun newInstance() = LogFragment()
    }
}