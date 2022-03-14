package com.example.moviehilt.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviehilt.R
import com.example.moviehilt.adapter.MovieAdapter
import com.example.moviehilt.viewModel.ListViewModel
import kotlinx.android.synthetic.main.fragment_list_movie.*


class ListMovieFragment : Fragment() {

    private lateinit var adapter: MovieAdapter
    private lateinit var viewModel: ListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(ListViewModel::class.java)

        viewModel._movie.observe(this, Observer {
            adapter = MovieAdapter(it)
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = adapter
        })

        swipeReflesh.setOnRefreshListener {
            recyclerView.visibility = View.GONE
            viewModel._movie.observe(this, Observer {
                adapter = MovieAdapter(it)
                recyclerView.layoutManager = LinearLayoutManager(context)
                recyclerView.adapter = adapter
                recyclerView.visibility = View.VISIBLE
                swipeReflesh.isRefreshing = false
            })
        }

    }
}