package com.test.sitec.sitectestlogin.presentation.ui.log

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.sitec.sitectestlogin.common.ALERT_DIALOG_TYPE_ERROR
import com.test.sitec.sitectestlogin.common.utils.AlertUtils
import com.test.sitec.sitectestlogin.data.datasources.db.models.LogItem
import com.test.sitec.sitectestlogin.databinding.FragmentLogBinding
import com.test.sitec.sitectestlogin.presentation.ui.base.BaseFragment

class LogFragment : BaseFragment(), LogContract.View {

    private var _vb: FragmentLogBinding? = null
    private val vb get() = _vb!!
    private lateinit var presenter: LogContract.Presenter
    private lateinit var itemsAdapter: LogAdapter

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
        presenter.getLog()
    }

    override fun setViews() {
        vb.logRv.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        itemsAdapter = LogAdapter()
        vb.logRv.adapter = itemsAdapter
    }

    override fun setClickListeners() {}

    override fun onClick(p0: View?) {}

    override fun showErrorDialog(message: String) {
        AlertUtils().showAlertDialog(
            requireContext(),
            ALERT_DIALOG_TYPE_ERROR,
            message,
            "Reply", "Close App", {

            }, { }
        )
    }

    override fun onLogReceived(logItemsList: List<LogItem>) {
        itemsAdapter.setList(logItemsList)
    }



    companion object {
        fun newInstance() = LogFragment()
    }


}