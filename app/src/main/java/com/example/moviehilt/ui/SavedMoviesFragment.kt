package com.example.moviehilt.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviehilt.R
import com.example.moviehilt.adapter.RoomAdapter
import com.example.moviehilt.model.Result
import com.example.moviehilt.viewModel.SavedFragmentViewModel
import kotlinx.android.synthetic.main.fragment_saved_movies.*

class SavedMoviesFragment : Fragment() {
    private lateinit var savedMovieViewModel:SavedFragmentViewModel
    private lateinit var singleMovieData:Result
    private lateinit var roomAdapter:RoomAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_saved_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        savedMovieViewModel = ViewModelProvider(requireActivity()).get(SavedFragmentViewModel::class.java)

        // Check if arguments null or not
        if(arguments?.get("movie")!= null) {
            if (requireArguments().getSerializable("movie") != null) {
                singleMovieData = requireArguments().getSerializable("movie") as Result

                val saveMovie = Result(singleMovieData.adult,
                    singleMovieData.backdrop_path,
                    singleMovieData.id,
                    singleMovieData.original_language,
                    singleMovieData.original_title,
                    singleMovieData.overview,
                    singleMovieData.popularity,
                    singleMovieData.poster_path,
                    singleMovieData.release_date,
                    singleMovieData.original_title,
                    singleMovieData.video,
                    singleMovieData.vote_average,
                    singleMovieData.vote_count)

                savedMovieViewModel.addMovie(saveMovie)
            }
        }

        saved_movie_recyclerview.layoutManager = LinearLayoutManager(context)

        savedMovieViewModel.readAllData.observe(viewLifecycleOwner, Observer {
            roomAdapter = RoomAdapter(it as ArrayList<Result>)
            saved_movie_recyclerview.adapter = roomAdapter
        })


    }


}