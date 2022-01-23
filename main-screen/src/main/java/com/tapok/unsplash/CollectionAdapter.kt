package com.tapok.unsplash

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import coil.load
import com.tapok.core.BaseViewHolder
import com.tapok.unsplash.databinding.CollectionItemBinding
import com.tapok.unsplash.model.Collection

internal class CollectionAdapter : ListAdapter<Collection, CollectionViewHolder>(CollectionDiffCallback()) {

    var onItemClicked: ((Collection) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CollectionItemBinding.inflate(layoutInflater, parent, false)
        return CollectionViewHolder(binding).apply {
            setOnClickListener(onItemClicked)
        }
    }

    override fun onBindViewHolder(holder: CollectionViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

internal class CollectionDiffCallback : DiffUtil.ItemCallback<Collection>() {
    override fun areItemsTheSame(oldItem: Collection, newItem: Collection): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Collection, newItem: Collection): Boolean =
        oldItem.id == newItem.id && oldItem.title == newItem.title && oldItem.size == newItem.size
}

internal class CollectionViewHolder(private val binding: CollectionItemBinding) :
    BaseViewHolder<Collection>(binding.root) {


    override fun bind(data: Collection) {
        super.bind(data)
        with(binding) {
            title.text = data.title
            collectionSize.text = data.size.toString()
            photo.placePhoto(data.cover)
        }
    }
}