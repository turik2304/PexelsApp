package com.example.pexelsapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pexelsapp.PexelsApp
import com.example.pexelsapp.R
import com.example.pexelsapp.domain.model.Photo
import com.example.pexelsapp.presentation.adapters.PhotosAdapter
import com.example.pexelsapp.presentation.view_models.PhotoViewModel
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchFragment : Fragment() {

    private lateinit var button: Button
    private lateinit var editText: EditText
    private lateinit var recyclerView: RecyclerView

    private lateinit var adapter: PhotosAdapter

    @Inject
    lateinit var pexelsViewModel: PhotoViewModel

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

        initViews(view)
        initRecycler()

        button.setOnClickListener {
            val query = editText.text.toString()
            pexelsViewModel.loadPhotos(query, 20)
        }

        lifecycleScope.launch {
            pexelsViewModel.photos.collectToAdapter()
        }

        pexelsViewModel.loadPhotos("sea", 1)
    }

    private fun initViews(parent: View) {
        button = parent.findViewById(R.id.button)
        editText = parent.findViewById(R.id.editText)
        recyclerView = parent.findViewById(R.id.recyclerView)
    }

    private fun initRecycler() {
        adapter = PhotosAdapter(pexelsViewModel::onPhotoClick)
        recyclerView.adapter = adapter
        recyclerView.hasFixedSize()
        val orientation = (recyclerView.layoutManager as LinearLayoutManager).orientation
        recyclerView.addItemDecoration(DividerItemDecoration(context, orientation))
    }

    private suspend fun SharedFlow<List<Photo>>.collectToAdapter() {
        this.collect {
            adapter.submitList(it)
        }
    }
}