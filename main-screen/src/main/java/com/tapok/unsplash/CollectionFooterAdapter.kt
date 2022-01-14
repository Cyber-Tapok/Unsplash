package com.tapok.unsplash

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tapok.unsplash.databinding.CollectionFooterBinding

internal class CollectionFooterAdapter : RecyclerView.Adapter<CollectionFooterViewHolder>() {

    var onFooterClicked: (() -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionFooterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CollectionFooterBinding.inflate(layoutInflater, parent, false)
        return CollectionFooterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CollectionFooterViewHolder, position: Int) {
        holder.itemView.setOnClickListener { onFooterClicked?.invoke() }
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.collection_footer
    }

    override fun getItemCount(): Int = 1
}


internal class CollectionFooterViewHolder(private val binding: CollectionFooterBinding) :
    RecyclerView.ViewHolder(binding.root)