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
import com.example.pexelsapp.domain.model.Photo
import com.example.pexelsapp.extensions.loadImageFromUrl

class PhotosAdapter(private val onClick: (Photo) -> Unit) :
    ListAdapter<Photo, PhotosAdapter.PhotoViewHolder>(PhotosDiffCallback) {

    class PhotoViewHolder(itemView: View, onClick: (Photo) -> Unit) :
        RecyclerView.ViewHolder(itemView) {

        private val imageViewPhoto = itemView.findViewById<ImageView>(R.id.imageViewPhoto)
        private val textViewDescription = itemView.findViewById<TextView>(R.id.textViewDescription)
        private var currentPhoto: Photo? = null

        init {
            imageViewPhoto.setOnClickListener {
                currentPhoto?.let {
                    onClick(it)
                }
            }
        }

        fun bind(photo: Photo) {
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

object PhotosDiffCallback : DiffUtil.ItemCallback<Photo>() {

    override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem.id == newItem.id
    }
}