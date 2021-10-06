package com.example.pexelsapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.pexelsapp.PexelsApp
import com.example.pexelsapp.R
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchFragment : Fragment() {

    private lateinit var button: Button
    private lateinit var imageView: ImageView
    private lateinit var editText: EditText

    @Inject
    lateinit var pexelsViewModel: PexelsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as PexelsApp).searchComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button = view.findViewById(R.id.button)
        imageView = view.findViewById(R.id.imageView)
        editText = view.findViewById(R.id.editText)

        button.setOnClickListener {
            val query = editText.text.toString()
            pexelsViewModel.loadImage(query, 1)
        }

        lifecycleScope.launch {
            pexelsViewModel.images
                .collect {
                    Glide.with(this@SearchFragment)
                        .load(it.photos?.first()?.source?.link)
                        .into(imageView)
                }
        }
        pexelsViewModel.loadImage("sea", 1)
    }
}