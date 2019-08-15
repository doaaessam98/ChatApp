package com.artf.chatapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.artf.chatapp.databinding.ItemMessageLeftBinding
import com.artf.chatapp.databinding.ItemMessageLeftImgBinding
import com.artf.chatapp.databinding.ItemMessageRightBinding
import com.artf.chatapp.databinding.ItemMessageRightImgBinding
import com.artf.chatapp.model.Message

class MsgAdapter(
    private val msgAdapterInt: MsgAdapterInt
) : ListAdapter<Message, RecyclerView.ViewHolder>(GridViewDiffCallback) {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val msg = getItem(position)
        (holder as MsgViewHolder<*>).bind(msgAdapterInt, msg)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_message_left -> MsgViewHolder(
                ItemMessageLeftBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            R.layout.item_message_right -> MsgViewHolder(
                ItemMessageRightBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            R.layout.item_message_left_img -> MsgViewHolder(
                ItemMessageLeftImgBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            R.layout.item_message_right_img -> MsgViewHolder(
                ItemMessageRightImgBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            else -> throw IllegalArgumentException("unknown view type $viewType")
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)!!
        return if (item.isOwner!!) {
            when {
                item.audioUrl != null -> R.layout.item_message_right_img
                item.photoUrl != null -> R.layout.item_message_right_img
                else -> R.layout.item_message_right
            }
        } else {
            when {
                item.audioUrl != null -> R.layout.item_message_left_img
                item.photoUrl != null -> R.layout.item_message_left_img
                else -> R.layout.item_message_left
            }
        }
    }

    class MsgViewHolder<T : ViewDataBinding> constructor(val binding: T) : RecyclerView.ViewHolder(binding.root) {

        fun bind(msgAdapterInt: MsgAdapterInt, item: Message) {
            when (binding) {
                is ItemMessageLeftBinding -> {
                    binding.message = item
                    binding.msgAdapterInt = msgAdapterInt
                }
                is ItemMessageRightBinding -> {
                    binding.message = item
                    binding.msgAdapterInt = msgAdapterInt
                }
                is ItemMessageLeftImgBinding -> {
                    binding.message = item
                    binding.msgAdapterInt = msgAdapterInt
                }
                is ItemMessageRightImgBinding -> {
                    binding.message = item
                    binding.msgAdapterInt = msgAdapterInt
                }
            }
            binding.executePendingBindings()
        }
    }

    companion object GridViewDiffCallback : DiffUtil.ItemCallback<Message>() {
        override fun areItemsTheSame(oldItem: Message, newItem: Message): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Message, newItem: Message): Boolean {
            return oldItem == newItem
        }
    }

    interface MsgAdapterInt {
        fun onAudioClick(view: View, message: Message)

    }

}