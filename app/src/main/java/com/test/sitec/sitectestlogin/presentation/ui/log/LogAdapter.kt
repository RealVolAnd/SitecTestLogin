package com.test.sitec.sitectestlogin.presentation.ui.log

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.sitec.sitectestlogin.R
import com.test.sitec.sitectestlogin.data.datasources.db.models.LogItem
import com.test.sitec.sitectestlogin.databinding.LogItemBinding
import com.test.sitec.sitectestlogin.domain.common.LOG_MESSAGE_TYPE_ERROR
import com.test.sitec.sitectestlogin.domain.common.LOG_MESSAGE_TYPE_SUCCESS

class LogAdapter : RecyclerView.Adapter<LogAdapter.ItemsViewHolder>() {
    var cards = arrayListOf<LogItem>()

    fun setList(cardList: List<LogItem>) {
        cards.clear()
        cards.addAll(cardList)
        notifyDataSetChanged()
    }


    inner class ItemsViewHolder(private val binding: LogItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: LogItem) {
            when (item.logMessageType) {
                LOG_MESSAGE_TYPE_SUCCESS -> binding.logItemImage.setImageResource(R.drawable.info_sign)
                LOG_MESSAGE_TYPE_ERROR -> binding.logItemImage.setImageResource(R.drawable.error_sign)
            }
            binding.logItemDate.text = item.timestamp
            binding.logItemText.text = item.logMessage
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        val vb = LogItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemsViewHolder(vb)
    }

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        holder.bind(cards[position])
    }

    override fun getItemCount() = cards.size
}