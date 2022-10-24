package com.test.sitec.sitectestlogin.presentation.ui.signin

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.test.sitec.sitectestlogin.presentation.models.User

class SpinnerAdapter(
    context: Context,
    private val textViewResourceId: Int,
    private val values: ArrayList<User>
) :
    ArrayAdapter<User>(context, textViewResourceId, values) {

    override fun getCount() = values.size

    override fun getItem(position: Int) = values[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val label = super.getView(position, convertView, parent) as TextView
        label.text = values[position].testUser
        return label
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val label = super.getDropDownView(position, convertView, parent) as TextView
        label.text = values[position].testUser
        return label
    }


} // BindableSpinnerAdapter class