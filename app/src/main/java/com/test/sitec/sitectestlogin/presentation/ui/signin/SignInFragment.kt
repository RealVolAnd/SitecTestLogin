package com.test.sitec.sitectestlogin.presentation.ui.signin

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.test.sitec.sitectestlogin.R
import com.test.sitec.sitectestlogin.common.ALERT_DIALOG_TYPE_ERROR
import com.test.sitec.sitectestlogin.common.ERROR_LOGIN_FAILED
import com.test.sitec.sitectestlogin.common.utils.AlertUtils
import com.test.sitec.sitectestlogin.data.datasources.network.models.requests.SignInRequest
import com.test.sitec.sitectestlogin.databinding.FragmentSignInBinding
import com.test.sitec.sitectestlogin.presentation.models.User
import com.test.sitec.sitectestlogin.presentation.ui.base.BaseFragment

class SignInFragment : BaseFragment() {
    private var _vb: FragmentSignInBinding? = null
    private val vb get() = _vb!!
    private val viewModel: SignInViewModel by viewModels()
    private lateinit var usersAdapter: BindableSpinnerAdapter
    private var isFieldsValid = false
    private var currentUid = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _vb = FragmentSignInBinding.inflate(inflater, container, false)
        setViews()
        return vb.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListeners()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getUsersListLiveData().observe(viewLifecycleOwner) { fillUsersList(it) }
        viewModel.getLiveData().observe(viewLifecycleOwner) { renderData(it) }
        lifecycle.addObserver(viewModel)
    }

    override fun setViews() {
        vb.signInUserName.setOnItemClickListener() { parent, _, position, id ->
            val selectedUser = parent.adapter.getItem(position) as User?
            currentUid = selectedUser!!.testUserUid
        }
        vb.signInConfirmBtn.isEnabled = false
        vb.signInUserName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                setConfirmButtonState()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
        vb.signInPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                setConfirmButtonState()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }

    override fun setClickListeners() {
        vb.signInConfirmBtn.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        if (p0 != null) {
            when (p0.id) {
                vb.signInConfirmBtn.id -> tryToSignIn()
            }
        }
    }

    private fun tryToSignIn() {
        viewModel.signIn(
            SignInRequest(
                currentUid,
                vb.signInPassword.text.toString(),
                false,
                ""
            )
        )
    }

    private fun renderData(appState: SignInLiveData) {
        when (appState) {
            is SignInLiveData.Loading -> {
            }
            is SignInLiveData.Success -> {
                when(appState.response.code){
                    1022 -> showLoginErrorDialog(ERROR_LOGIN_FAILED)
                    else -> goToLogScreen()
                }
            }
            is SignInLiveData.Error -> {
                showErrorDialog(appState.error)
            }
        }
    }

    private fun fillUsersList(usersList: ArrayList<User>) {
        usersAdapter =
            BindableSpinnerAdapter(requireContext(), android.R.layout.simple_list_item_1, usersList)
        usersAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        vb.signInUserName.setAdapter(usersAdapter)
    }

    fun showErrorDialog(message: String) {
        AlertUtils().showAlertDialog(
            requireContext(),
            ALERT_DIALOG_TYPE_ERROR,
            message,
            "Reply", "Close App", {
                //viewModel.getUsers()
            }, { requireActivity().finish() }
        )
    }

    fun showLoginErrorDialog(message: String) {
        AlertUtils().showAlertDialog(
            requireContext(),
            ALERT_DIALOG_TYPE_ERROR,
            message,
            "Ok", "", {

            }, {}
        )
    }

    private fun setConfirmButtonState() {
        vb.signInConfirmBtn.isEnabled =
            vb.signInUserName.text.isNotEmpty() && vb.signInPassword.text!!.isNotEmpty()
    }


    private fun goToLogScreen() {
        findNavController().navigate(R.id.action_signInFragment_to_logFragment)
    }

    companion object {
        fun newInstance() = SignInFragment()
    }
}