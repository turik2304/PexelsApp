package com.example.pexelsapp.presentation.screens.search_photo

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pexelsapp.PexelsApp
import com.example.pexelsapp.R
import com.example.pexelsapp.presentation.adapters.PhotosAdapter
import com.example.pexelsapp.presentation.model.PhotoUI
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
class SearchFragment : Fragment() {

    companion object {
        private const val PHOTOS_PER_PAGE = 20
        private const val PHOTOS_PER_PAGE_INITIAL = 20
        private const val INITIAL_QUERY = "hello"
    }

    private lateinit var editText: EditText
    private lateinit var recyclerView: RecyclerView

    private lateinit var adapter: PhotosAdapter

    @Inject
    lateinit var viewModel: SearchViewModel

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
            if (query.isNotBlank()) {
                viewModel.loadPhotos(query, PHOTOS_PER_PAGE)
            }
        }

        override fun afterTextChanged(s: Editable?) {
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as PexelsApp).searchComponent.inject(this)
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

        initViews(view)
        initRecycler()

        lifecycleScope.launch {
            viewModel.photosFlow.collectToAdapter()
        }

        viewModel.loadPhotos(INITIAL_QUERY, PHOTOS_PER_PAGE_INITIAL)
    }

    private fun initViews(parent: View) {
        editText = parent.findViewById(R.id.editText)
        editText.addTextChangedListener(queryTextWatcher)
        recyclerView = parent.findViewById(R.id.recyclerView)
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
}