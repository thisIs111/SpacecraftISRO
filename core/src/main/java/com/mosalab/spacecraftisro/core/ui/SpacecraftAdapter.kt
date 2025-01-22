package com.mosalab.spacecraftisro.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mosalab.spacecraftisro.core.databinding.ItemListSpacecraftBinding
import com.mosalab.spacecraftisro.core.domain.model.Spacecraft

class SpacecraftAdapter : ListAdapter<Spacecraft, SpacecraftAdapter.ListViewHolder>(DIFF_CALLBACK) {
    var onItemClick: ((Spacecraft) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(
            ItemListSpacecraftBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
    }

    inner class ListViewHolder(private var binding: ItemListSpacecraftBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Spacecraft) {

            binding.tvItemTitle.text = data.spacecraftId
            binding.tvItemSubtitle.text = data.name
        }

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(getItem(bindingAdapterPosition))
            }
        }
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Spacecraft> =
            object : DiffUtil.ItemCallback<Spacecraft>() {
                override fun areItemsTheSame(oldItem: Spacecraft, newItem: Spacecraft): Boolean {
                    return oldItem.spacecraftId == newItem.spacecraftId
                }

                override fun areContentsTheSame(oldItem: Spacecraft, newItem: Spacecraft): Boolean {
                    return oldItem == newItem
                }
            }
    }
}