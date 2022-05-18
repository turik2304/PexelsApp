package com.example.pexelsapp.presentation.screens.search_photo

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pexelsapp.R
import com.example.pexelsapp.presentation.adapters.PhotosAdapter
import com.example.pexelsapp.presentation.model.PhotoUI
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

@FlowPreview
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val viewModel by viewModels<SearchViewModel>()

    private lateinit var editText: EditText

    private lateinit var recyclerView: RecyclerView

    private lateinit var adapter: PhotosAdapter

    private val queryTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(
            s: CharSequence?,
            start: Int,
            count: Int,
            after: Int
        ) {
        }

        override fun onTextChanged(
            charSequence: CharSequence,
            start: Int,
            before: Int,
            count: Int
        ) {
            val query = charSequence.toString().trim()
            viewModel.loadPhotos(query, PHOTOS_PER_PAGE)
        }

        override fun afterTextChanged(s: Editable?) {}
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        editText = view.findViewById(R.id.editText)
        editText.addTextChangedListener(queryTextWatcher)
        recyclerView = view.findViewById(R.id.recyclerView)

        initRecycler()

        lifecycleScope.launch {
            viewModel.photosFlow.collectToAdapter()
        }

        viewModel.loadPhotos(INITIAL_QUERY, PHOTOS_PER_PAGE_INITIAL)
    }

    private fun initRecycler() {
        adapter = PhotosAdapter(viewModel::onPhotoClick)
        recyclerView.adapter = adapter
        recyclerView.hasFixedSize()
        val orientation = (recyclerView.layoutManager as LinearLayoutManager).orientation
        recyclerView.addItemDecoration(DividerItemDecoration(context, orientation))
    }

    private suspend fun SharedFlow<List<PhotoUI>>.collectToAdapter() {
        this.collect {
            adapter.submitList(it)
        }
    }

    companion object {
        private const val PHOTOS_PER_PAGE = 20
        private const val PHOTOS_PER_PAGE_INITIAL = 20
        private const val INITIAL_QUERY = "hello"
    }
}