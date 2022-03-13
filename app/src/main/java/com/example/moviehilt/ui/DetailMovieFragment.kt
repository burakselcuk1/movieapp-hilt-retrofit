package com.example.moviehilt.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.example.moviehilt.R
import com.example.moviehilt.Util.Cons.POSTER_MAIN_URL
import com.example.moviehilt.model.Result
import com.example.moviehilt.viewModel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_detail_movie.*


class DetailMovieFragment : Fragment() {

    private lateinit var viewModel: DetailViewModel
    lateinit var resultMovie:Result

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

            it.let {
                resultMovie = it
                movieDetailName.text = resultMovie.original_title
                val url= POSTER_MAIN_URL + resultMovie.poster_path
                Glide.with(this).load(url).into(movieDetailPoster)
                movieDetailDescription.text = resultMovie.overview
            }
        })

        saveMovieToRoomDb.setOnClickListener {
            var bundle = Bundle()
            resultMovie.let {
                bundle.putSerializable("movie", resultMovie)
                val navigationController = Navigation.findNavController(view)
                navigationController.navigate(R.id.action_detailMovieFragment_to_savedMoviesFragment, bundle)
            }
        }

    }
}