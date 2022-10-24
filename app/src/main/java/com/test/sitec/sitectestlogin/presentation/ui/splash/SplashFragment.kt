package com.test.sitec.sitectestlogin.presentation.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.test.sitec.sitectestlogin.R
import com.test.sitec.sitectestlogin.common.ALERT_DIALOG_TYPE_ERROR
import com.test.sitec.sitectestlogin.common.utils.AlertUtils
import com.test.sitec.sitectestlogin.databinding.FragmentSplashBinding
import com.test.sitec.sitectestlogin.presentation.ui.base.BaseFragment


class SplashFragment : BaseFragment() {

    companion object {
        fun newInstance() = SplashFragment()
    }

    private var _vb: FragmentSplashBinding? = null
    private val vb get() = _vb!!
    private val viewModel: SplashViewModel by viewModels()

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
        _vb = FragmentSplashBinding.inflate(inflater, container, false)
        setViews()
        return vb.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListeners()
        viewModel.getUsers()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getLiveData().observe(viewLifecycleOwner) { renderData(it) }
        lifecycle.addObserver(viewModel)
    }

    override fun setViews() {
    }

    override fun setClickListeners() {
    }

    override fun onClick(p0: View?) {
    }

    private fun renderData(appState: SplashLiveData) {
        when (appState) {
            is SplashLiveData.Loading -> {
            }
            is SplashLiveData.Success -> {
               goToSignInScreen()
            }
            is SplashLiveData.Error -> {
                showErrorDialog(appState.error)
            }
        }
    }

   fun showErrorDialog(message: String) {
        AlertUtils().showAlertDialog(
            requireContext(),
            ALERT_DIALOG_TYPE_ERROR,
            message,
            "Reply", "Close App", {
                viewModel.getUsers()
            }, { requireActivity().finish() }
        )
    }


    private fun goToSignInScreen() {
        findNavController().navigate(R.id.action_splashFragment_to_signInFragment)
    }
}