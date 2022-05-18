package com.example.pexelsapp.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pexelsapp.R
import com.example.pexelsapp.extensions.loadImageFromUrl
import com.example.pexelsapp.presentation.model.PhotoUI

class PhotosAdapter(private val onClick: (PhotoUI) -> Unit) :
    ListAdapter<PhotoUI, PhotosAdapter.PhotoViewHolder>(PhotosDiffCallback) {

    class PhotoViewHolder(itemView: View, onClick: (PhotoUI) -> Unit) :
        RecyclerView.ViewHolder(itemView) {

        private val imageViewPhoto = itemView.findViewById<ImageView>(R.id.imageViewPhoto)
        private val textViewDescription = itemView.findViewById<TextView>(R.id.textViewDescription)
        private var currentPhoto: PhotoUI? = null

        init {
            imageViewPhoto.setOnClickListener {
                currentPhoto?.let {
                    onClick(it)
                }
            }
        }

        fun bind(photo: PhotoUI) {
            currentPhoto = photo
            textViewDescription.text = photo.photographer
            imageViewPhoto.loadImageFromUrl(photo.url)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val photoView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_pexels, parent, false)
        return PhotoViewHolder(photoView, onClick)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val photo = getItem(position)
        holder.bind(photo)
    }
}

object PhotosDiffCallback : DiffUtil.ItemCallback<PhotoUI>() {

    override fun areItemsTheSame(oldItem: PhotoUI, newItem: PhotoUI): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: PhotoUI, newItem: PhotoUI): Boolean {
        return oldItem.id == newItem.id
    }
}