package com.test.sitec.sitectestlogin.domain.common.utils

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.test.sitec.sitectestlogin.R
import com.test.sitec.sitectestlogin.databinding.DialogAlertBinding
import com.test.sitec.sitectestlogin.domain.common.ALERT_DIALOG_TYPE_ERROR
import com.test.sitec.sitectestlogin.domain.common.ALERT_DIALOG_TYPE_INFO
import com.test.sitec.sitectestlogin.domain.common.ALERT_DIALOG_TYPE_WARN

class AlertUtils {
    fun showAlertDialog(
        context: Context,
        type: Int,
        message: String,
        okButtonText: String,
        noButtonText: String,
        callbackOk: (() -> Unit)? = null,
        callbackNo: (() -> Unit)? = null

    ) {
        val binding: DialogAlertBinding? =
            DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.dialog_alert,
                null,
                false
            )


        when (type) {
            ALERT_DIALOG_TYPE_ERROR -> {
                binding!!.dialogTitle.text = context.getString(R.string.error)
                binding.dialogIcon.setImageResource(R.drawable.error_sign)
            }
            ALERT_DIALOG_TYPE_WARN -> {
                binding!!.dialogTitle.text = context.getString(R.string.warning)
                binding.dialogIcon.setImageResource(R.drawable.warn_sign)
            }
            ALERT_DIALOG_TYPE_INFO -> {
                binding!!.dialogTitle.text = context.getString(R.string.info)
                binding.dialogIcon.setImageResource(R.drawable.info_sign)
            }
        }

        binding!!.okButton.text = okButtonText
        if (noButtonText.isNotBlank()) {
            binding.noButton.text = noButtonText
            binding.noButton.visibility = View.VISIBLE
        } else {
            binding.noButton.visibility = View.GONE
        }
        binding.dialogMessage.text = message


        val customDialog = MaterialAlertDialogBuilder(context).create()

        customDialog.apply {
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setView(binding.root)
            setCancelable(false)
        }.show()

        binding.okButton.setOnClickListener {
            callbackOk?.invoke()
            customDialog.dismiss()
        }

        binding.noButton.setOnClickListener {
            callbackNo?.invoke()
            customDialog.dismiss()
        }
    }

    fun showSystemMessage(
        view: View,
        message: String, type: Int
    ) {
        val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)

        when (type) {
            ALERT_DIALOG_TYPE_ERROR -> snackbar.view.backgroundTintList = ColorStateList.valueOf(
                Color.parseColor("#d42424")
            )
            ALERT_DIALOG_TYPE_WARN -> snackbar.view.backgroundTintList = ColorStateList.valueOf(
                Color.parseColor("#DC8401")
            )
            ALERT_DIALOG_TYPE_INFO -> snackbar.view.backgroundTintList = ColorStateList.valueOf(
                Color.parseColor("#067B6D")
            )
        }

        snackbar.view.alpha = 0.5f
        snackbar.view.setBackgroundResource(R.drawable.system_alert_shape)
        snackbar.show()
    }


}