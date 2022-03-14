package com.example.moviehilt.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.moviehilt.R
import com.example.moviehilt.Util.Cons
import com.example.moviehilt.model.Result
import com.example.moviehilt.viewModel.SavedMoviesDetailViewModel
import kotlinx.android.synthetic.main.fragment_detail_movie.*
import kotlinx.android.synthetic.main.fragment_saved_movies_detail.*


class SavedMoviesDetailFragment : Fragment() {

    private lateinit var viewModel:SavedMoviesDetailViewModel
    lateinit var resultMovie: Result



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_saved_movies_detail, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(SavedMoviesDetailViewModel::class.java)


        val args = this.arguments
        val movieId: String? = args?.getString("movieIdd","databoss")

        viewModel.getMovieDetailForSavedMovie(movieId.toString())

        viewModel._movieDetail.observe(this, Observer {
            resultMovie = it
            movieDetailNameSaved.text = resultMovie.original_title
            val url= Cons.POSTER_MAIN_URL + resultMovie.poster_path
            Glide.with(this).load(url).into(movieDetailPosterSaved)
            movieDetailDescriptionSaved.text = resultMovie.overview
        })

        deleteMovieFromRoomDb.setOnClickListener {
            deleteMovie()
        }
    }
    private fun deleteMovie() {
        viewModel._movieDetail.observe(viewLifecycleOwner, Observer {
            val builder = AlertDialog.Builder(requireContext())
            builder.setPositiveButton("Yes"){_, _ ->
                viewModel.deleteMovie(it)
                findNavController().navigate(R.id.action_savedMoviesDetailFragment_to_savedMoviesFragment)
                Toast.makeText(requireContext(),"Movie deleted!", Toast.LENGTH_SHORT).show()


            }
            builder.setNegativeButton("No"){_, _ ->}
            builder.setTitle("Delete ${it.original_title}")
            builder.setMessage("Are you sure delete this movie ${it.original_title} ?")
            builder.create().show()

        })
    }


}