package com.example.nbc_sunnyus.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nbc_sunnyus.R
import com.example.nbc_sunnyus.databinding.ItemEvenBinding
import com.example.nbc_sunnyus.databinding.ItemOddBinding
import com.example.nbc_sunnyus.model.UserInfo
import java.lang.Exception

class ContactListAdapter(private val items: MutableList<UserInfo>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var itemClickListener: OnItemClickListener

    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }

    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }

    fun getItem(position: Int): UserInfo {
        return items[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            EVEN_VIEW_TYPE -> {
                val binding = ItemEvenBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                EvenViewHolder(binding)
            }

            ODD_VIEW_TYPE -> {
                val binding = ItemOddBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                OddViewHolder(binding)
            }

            else -> throw Exception("Type Error") // 선택된 Type이 없을때
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        when (holder.itemViewType) {
            EVEN_VIEW_TYPE -> {
                (holder as EvenViewHolder).bind(item)
            }

            ODD_VIEW_TYPE -> {
                (holder as OddViewHolder).bind(item)
            }
        }
        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position % 2 == 0) {
            EVEN_VIEW_TYPE
        } else {
            ODD_VIEW_TYPE
        }
    }

    companion object {
        const val EVEN_VIEW_TYPE = 0 // 짝수 multi-type
        const val ODD_VIEW_TYPE = 1 // 홀수 multi-type
    }

    class EvenViewHolder(private val binding: ItemEvenBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var isClickEvent = false

        fun bind(item: UserInfo) {
            binding.apply {
                ivImage.setImageResource(item.image)
                tvPhoneNumber.text = item.phoneNumber

                ivFavorite.setOnClickListener {
                    isClickEvent = !isClickEvent

                    if (isClickEvent) {
                        ivFavorite.setImageResource(R.drawable.img_favorite)
                    } else {
                        ivFavorite.setImageResource(R.drawable.img_empty_favorite)
                    }
                }
            }
        }
    }

    class OddViewHolder(private val binding: ItemOddBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var isClickEvent = false

        fun bind(item: UserInfo) {
            binding.apply {
                ivImage.setImageResource(item.image)
                tvPhoneNumber.text = item.phoneNumber

                ivFavorite.setOnClickListener {
                    isClickEvent = !isClickEvent

                    if (isClickEvent) {
                        ivFavorite.setImageResource(R.drawable.img_favorite)
                    } else {
                        ivFavorite.setImageResource(R.drawable.img_empty_favorite)
                    }
                }
            }
        }
    }
}