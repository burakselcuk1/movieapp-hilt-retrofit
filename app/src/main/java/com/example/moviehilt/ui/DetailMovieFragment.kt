package com.example.moviehilt.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.moviehilt.R
import com.example.moviehilt.Util.Cons.POSTER_MAIN_URL
import com.example.moviehilt.viewModel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_detail_movie.*


class DetailMovieFragment : Fragment() {

    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = this.arguments
        val movieId: String? = args?.getString("movieId","databoss")
      //  Log.e("Brk:DetailMovieFragment",movieId.toString())

        viewModel = ViewModelProvider(requireActivity()).get(DetailViewModel::class.java)

        viewModel.getMovieDetail(movieId.toString())

        viewModel._movieDetail.observe(this, Observer {

            it.let { response ->
                movieDetailName.text = response.original_title
                val url= POSTER_MAIN_URL + response.poster_path
                Glide.with(this).load(url).into(movieDetailPoster)
                movieDetailDescription.text = response.overview
            }
        })
    }
}